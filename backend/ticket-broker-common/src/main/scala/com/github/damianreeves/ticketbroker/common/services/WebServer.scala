package com.github.damianreeves.ticketbroker.common.services

import com.github.damianreeves.ticketbroker.common.config.Configuration.WebConfig
import scalaz.zio.{Task, ZIO}

object WebServer {
  case class WebServerRef(hostname:String, port:Int, shutdownHook:ZIO[Any,Nothing,Unit])

  trait Service {
    def start(webConfig:WebConfig):Task[WebServerRef]
    def stop(webServerRef: WebServerRef):ZIO[Any, Nothing, Unit]
  }

  implicit class WebServerServiceOps(self:Service) {

    def run(
      webConfig: WebConfig,
      onStarted:WebServerRef => ZIO[Any,Nothing, Unit] = _ => ZIO.succeed(())
    ) : ZIO[Any, Throwable, Unit] =
      ZIO.bracket(self.start(webConfig)){webServerRef =>
      self.stop(webServerRef)
    }{ wsRef =>
        onStarted(wsRef)
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
