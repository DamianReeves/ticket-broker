package com.github.damianreeves.ticketbroker

import scalaz.zio.ZIO

package object kafka {
  final val kafkaPlatformService:ZIO[KafkaStreamsPlatform, Nothing, KafkaStreamsPlatform.Service] =
    ZIO.access(_.kafkaStreamsPlatform)


}
