package com.github.damianreeves.ticketbroker.akka.server.services

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import com.github.damianreeves.ticketbroker.akka.server.services
import com.github.damianreeves.ticketbroker.common.config.Configuration
import com.github.damianreeves.ticketbroker.common.config.Configuration.WebConfig
import com.github.damianreeves.ticketbroker.common.services.WebServer
import com.github.damianreeves.ticketbroker.common.services.WebServer.WebServerRef
import com.typesafe.scalalogging.LazyLogging
import scalaz.zio.{Task, ZIO}

import scala.concurrent.ExecutionContextExecutor
import scala.concurrent.duration._

class AkkaWebServer(routes:Route, system: ActorSystem, materializer: ActorMaterializer) extends WebServer {

  override val webServer: WebServer.Service =
    new services.AkkaWebServer.WebServerLive(routes, system, materializer)
}

object AkkaWebServer {

  def apply(routes: Route)(implicit system: ActorSystem, materializer: ActorMaterializer) =
    new AkkaWebServer(routes, system, materializer)

  private class WebServerLive(routes:Route, actorSystem: ActorSystem, actorMaterializer: ActorMaterializer)
    extends WebServer.Service
    with LazyLogging {
    implicit val system:ActorSystem = actorSystem
    implicit val materializer:ActorMaterializer = actorMaterializer
    implicit val executionContext: ExecutionContextExecutor = system.dispatcher

    override def start(webConfig: Configuration.WebConfig): Task[WebServer.WebServerRef] = for {
      _ <- ZIO.succeed { logger.info(s"Starting web server on host: ${webConfig.hostname} and port: ${webConfig.port}") }
      ref <- bindAndHandle(webConfig)
      _ <- ZIO.succeed { logger.info(s"Now serving requests on host: ${webConfig.hostname} and port: ${webConfig.port}") }
    } yield ref


    override def stop(webServerRef: WebServer.WebServerRef): ZIO[Any, Nothing, Unit] =
      webServerRef.shutdownHook

    private def bindAndHandle(webConfig: WebConfig) = {
      ZIO.fromFuture { _ =>
        Http().bindAndHandle(routes, webConfig.hostname, webConfig.port)
      }.map { serverBinding =>
        val address = serverBinding.localAddress
        val shutdownHook = for {
          timeout <- ZIO.succeed(10.seconds)
          _ <- ZIO.fromFuture { _ =>
            serverBinding.terminate(timeout)
          }.fold(_ => (), _ => ())
        } yield ()

        WebServerRef(
          address.getHostName,
          address.getPort,
          shutdownHook
        )
      }
    }
  }
}
