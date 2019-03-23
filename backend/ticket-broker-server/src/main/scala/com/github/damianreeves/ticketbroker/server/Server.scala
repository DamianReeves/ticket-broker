package com.github.damianreeves.ticketbroker.server

import com.typesafe.scalalogging.LazyLogging

object Server extends LazyLogging {
  def main(args: Array[String]): Unit = {
    logger.info("Hello from the Ticket Broker Server")
  }
}
