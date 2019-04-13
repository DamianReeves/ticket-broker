package com.github.damianreeves.ticketbroker.akka.server

import com.typesafe.scalalogging.LazyLogging
import scalaz.zio.clock.Clock
import scalaz.zio.{App, Promise, ZIO}
import scalaz.zio.console._
import scalaz.zio.system.System
import scalaz.zio.duration._

object AkkaTicketBrokerServer extends App with LazyLogging {
  override def run(args: List[String]): ZIO[AkkaTicketBrokerServer.Environment, Nothing, Int] = {
    def mkEnv() = new Console.Live with Clock.Live with System.Live
    program.provide(mkEnv())
  }

  val program: ZIO[Console with Any with Clock, Nothing, Int] = for {
    promise <- Promise.make[Nothing,String]
    _ <- promise.succeed("Good bye cruel world!").delay(3.seconds).fork
    _ <- putStrLn("Hello World")
    value <- promise.await
    _ <- putStrLn(value)
  } yield 1
}
