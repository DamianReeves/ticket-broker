package com.github.damianreeves.ticketbroker.console

import scalaz.zio.clock.Clock
import scalaz.zio.console.{Console, putStrLn}
import scalaz.zio.duration._
import scalaz.zio.{App, Promise, ZIO}

object ConsoleApplication extends App {
  override def run(args: List[String]): ZIO[ConsoleApplication.Environment, Nothing, Int] = {
    def mkEnv() = new Console.Live with Clock.Live
    program.provide(mkEnv()).map(_ => 0)
  }

  val program = for {
    promise <- Promise.make[Nothing,String]
    _ <- promise.succeed("Good bye cruel world!").delay(3.seconds).fork
    _ <- putStrLn("Hello World")
    value <- promise.await
    _ <- putStrLn(value)
  } yield ()

}
