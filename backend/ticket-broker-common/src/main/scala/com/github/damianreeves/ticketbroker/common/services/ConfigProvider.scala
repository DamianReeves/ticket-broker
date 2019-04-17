package com.github.damianreeves.ticketbroker.common.services

import com.github.damianreeves.ticketbroker.common.config.Configuration
import com.github.damianreeves.ticketbroker.common.services.ProfileManager.ActiveProfiles
import com.typesafe.config.Config
import scalaz.zio.{Task, ZIO}

object ConfigProvider {
  trait Service {
    def loadConfig(activeProfiles:ActiveProfiles):Task[Config]
  }

  implicit class ConfigProviderServiceOps(self: Service) {
    import pureconfig.generic.auto._

    def loadConfiguration(activeProfiles: ActiveProfiles):Task[Configuration] = for {
      config <- self.loadConfig(activeProfiles)
      configuration <- ZIO {
        pureconfig.loadConfigOrThrow[Configuration](config)
      }
    } yield configuration
  }

  trait Syntax {
    def loadConfig(activeProfiles:ActiveProfiles):ZIO[ConfigProvider, Throwable, Config] =
      ZIO.accessM(_.configProvider.loadConfig(activeProfiles))

    def loadConfiguration(activeProfiles:ActiveProfiles):ZIO[ConfigProvider, Throwable, Configuration] =
      ZIO.accessM(_.configProvider.loadConfiguration(activeProfiles))
  }
}

trait ConfigProvider {
  def configProvider:ConfigProvider.Service
}
