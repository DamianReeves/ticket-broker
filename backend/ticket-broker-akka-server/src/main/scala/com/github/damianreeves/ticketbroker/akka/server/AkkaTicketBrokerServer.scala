package com.github.damianreeves.ticketbroker.akka.server

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.github.damianreeves.ticketbroker.akka.server.services.AkkaWebServer
import com.github.damianreeves.ticketbroker.common.services.ProfileManager.ActiveProfiles
import com.github.damianreeves.ticketbroker.common.services.{ConfigProvider, configProvider, webServer}
import com.typesafe.scalalogging.LazyLogging
import scalaz.zio.{App, UIO, ZIO}
import akka.http.scaladsl.server.Route
import com.github.damianreeves.ticketbroker.common.config.Configuration.WebConfig
import scalaz.zio.console.Console

object AkkaTicketBrokerServer extends App with LazyLogging {
  override def run(args: List[String]): ZIO[AkkaTicketBrokerServer.Environment, Nothing, Int] = {
    implicit val system: ActorSystem = ActorSystem("ticketbroker-web-server")
    implicit val materializer: ActorMaterializer = ActorMaterializer()

    def mkEnv() =
      new ConfigProvider.Live
        with Console.Live

    program
      .foldM(err => ZIO.succeed (logger.error("Error encountered:",err)).map(_=>1),
        _ => ZIO.succeed(logger.info("Execution complete.")).map(_ => 0)
      )
      .provide(mkEnv())
  }

  def program(implicit system: ActorSystem, materializer: ActorMaterializer): ZIO[ConfigProvider, Throwable, Int] = for {
    _ <- ZIO.succeed { logger.info("Starting...")}
    config <- configProvider.loadConfiguration(ActiveProfiles.default)
    ws <- createWebServer(config.web)
    _ <- webServer.run(config.web, _ => ZIO.succeed(()).fork.forever).provide(ws)
  } yield 0


  private def routes(webConfig: WebConfig): UIO[Route] = {
    import com.github.damianreeves.ticketbroker.akka.server.web._
    import akka.http.scaladsl.server.Directives._

    ZIO.succeed {
        AdminHttpService().route ~
        HomeHttpService().route ~
        SwaggerDocService(webConfig.hostname, webConfig.port).routes
    }
  }

  private def createWebServer(webConfig:WebConfig)(implicit
    system: ActorSystem,
    materializer: ActorMaterializer)
  : ZIO[Any, Throwable, AkkaWebServer] = for {
    routes <- routes(webConfig)
    webServer <- ZIO{AkkaWebServer(routes)}
  } yield webServer

}
