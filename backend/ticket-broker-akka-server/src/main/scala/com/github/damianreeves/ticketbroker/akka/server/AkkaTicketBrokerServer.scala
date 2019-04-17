package com.github.damianreeves.ticketbroker.akka.server

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.github.damianreeves.ticketbroker.akka.server.services.AkkaWebServer
import com.github.damianreeves.ticketbroker.common.services.ProfileManager.ActiveProfiles
import com.github.damianreeves.ticketbroker.common.services.{ConfigProvider, WebServer, configProvider, webServer}
import com.typesafe.scalalogging.LazyLogging
import scalaz.zio.{App, ZIO}
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import scalaz.zio.console.Console

object AkkaTicketBrokerServer extends App with LazyLogging {
  override def run(args: List[String]): ZIO[AkkaTicketBrokerServer.Environment, Nothing, Int] = {
    implicit val system = ActorSystem("ticketbroker-web-server")
    implicit val materializer = ActorMaterializer()
    val route = path("hello") {
      get {
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Say hello to akka-http</h1>"))
      }
    }
    def mkEnv(): WebServer with ConfigProvider =
      new AkkaWebServer(route, system, materializer)
        with ConfigProvider.Live
        with Console.Live
    program
      .foldM(err => ZIO.succeed (logger.error("Error encountered:",err)).map(_=>1),
        _ => ZIO.succeed(logger.info("Execution complete.")).map(_ => 0)
      )
      .provide(mkEnv())
  }

  val program: ZIO[WebServer with ConfigProvider, Throwable, Int] = for {
    _ <- ZIO.succeed { logger.info("Starting...")}
    config <- configProvider.loadConfiguration(ActiveProfiles.default)
    _ <- webServer.run(config.web, webServerRef => ZIO.succeed(()).fork.forever)
  } yield 0
}
