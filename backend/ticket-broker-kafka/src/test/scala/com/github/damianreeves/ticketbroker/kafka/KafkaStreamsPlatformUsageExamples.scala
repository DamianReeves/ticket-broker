package com.github.damianreeves.ticketbroker.kafka

import scalaz.zio.clock.Clock
import scalaz.zio.internal.PlatformLive
import scalaz.zio.{App, Promise, Runtime, ZIO}
import scalaz.zio.duration._

object KafkaStreamsPlatformUsageExamples extends App {

  import scalaz.zio.console._

  type AppEnvironment = KafkaStreamsPlatform with Console with Clock
  object AppEnvironment {
    def apply(): AppEnvironment =
      new KafkaStreamsPlatform.Live
        with Console.Live
        with Clock.Live
  }
  val myRuntime = Runtime(Environment,PlatformLive.Default)
  val program = for {
    promise <- Promise.make[Nothing,String]
    _ <- promise.succeed("Good bye cruel world!").delay(3.seconds).fork
    _ <- putStrLn("Hello World")
    value <- promise.await
    _ <- putStrLn(value)
  } yield ()

  override def run(args: List[String]): ZIO[KafkaStreamsPlatformUsageExamples.Environment, Nothing, Int] = {
    program.provide(AppEnvironment()).map(_ => 0)
  }


}
