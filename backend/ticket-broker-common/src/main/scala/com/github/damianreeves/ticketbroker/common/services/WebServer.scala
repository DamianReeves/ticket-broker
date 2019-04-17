package com.github.damianreeves.ticketbroker.common.services

import java.net.URL

import com.github.damianreeves.ticketbroker.common.config.Configuration.WebConfig
import com.typesafe.scalalogging.LazyLogging
import scalaz.zio.{Task, ZIO}


object WebServer {
  case class WebServerRef(hostname:String, port:Int, shutdownHook:ZIO[Any,Nothing,Unit]) {
    def url:URL = new URL("http", hostname, port,"/")
  }

  trait Service {
    def start(webConfig:WebConfig):Task[WebServerRef]
    def stop(webServerRef: WebServerRef):ZIO[Any, Nothing, Unit]
  }

  implicit class WebServerServiceOps(self:Service) extends LazyLogging {

    def run(
      webConfig: WebConfig,
      onStarted:WebServerRef => ZIO[Any,Nothing, Unit] = _ => ZIO.succeed(())
    ) : ZIO[Any, Throwable, Unit] =
      ZIO.bracket(self.start(webConfig)){webServerRef =>
      self.stop(webServerRef)
    }{ wsRef => for {
        _ <- ZIO.succeed { logger.info(s"Started web server at: ${wsRef.url}")}
        _ <- onStarted(wsRef)
      } yield ()
    }
  }

  trait Syntax {
    def start(webConfig:WebConfig):ZIO[WebServer, Throwable, WebServerRef] =
      ZIO.accessM(_.webServer.start(webConfig))

    def stop(webServerRef: WebServerRef):ZIO[WebServer, Nothing, Unit] =
      ZIO.accessM(_.webServer.stop(webServerRef))

    def run(
      webConfig:WebConfig,
      onStarted:WebServerRef => ZIO[Any,Nothing, Unit] = _ => ZIO.succeed(())
    ):ZIO[WebServer, Throwable, Unit] =
      ZIO.accessM(_.webServer.run(webConfig, onStarted))
  }
}

trait WebServer {
  def webServer:WebServer.Service
}
