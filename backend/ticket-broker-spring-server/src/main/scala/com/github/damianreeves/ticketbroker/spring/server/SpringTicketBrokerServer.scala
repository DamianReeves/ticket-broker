package com.github.damianreeves.ticketbroker.spring.server

import com.typesafe.scalalogging.LazyLogging
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder

@SpringBootApplication
class SpringTicketBrokerServer extends CommandLineRunner with LazyLogging {
  override def run(args: String*): Unit = {
    logger.info("Running...")
  }
}

object SpringTicketBrokerServer extends LazyLogging {
  def main(args: Array[String]): Unit = {
    new SpringApplicationBuilder(classOf[SpringTicketBrokerServer])
      .run(args:_*)
    ()
  }
}
