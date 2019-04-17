package com.github.damianreeves.ticketbroker.common.config

import com.github.damianreeves.ticketbroker.common.config.Configuration.{AppConfig, WebConfig}

case class Configuration(app:AppConfig, web:WebConfig)

object Configuration {
  final case class AppConfig(name:String, profiles:Map[String, ProfileConfig])
  final case class WebConfig(hostname:String, port:Int)
  final case class ProfileConfig(priority:Int)
}
