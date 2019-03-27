package com.github.damianreeves.ticketbroker.console

import java.io.IOException

import com.github.damianreeves.ticketbroker.common.model.domain.events.valuetypes.EventSummary
import scalaz.zio.clock.Clock
import scalaz.zio.console.{Console, getStrLn, putStrLn}
import scalaz.zio.system.{System,lineSeparator}
import scalaz.zio.duration._
import scalaz.zio.{App, Promise, ZIO}

object ConsoleApplication extends App {
  override def run(args: List[String]): ZIO[ConsoleApplication.Environment, Nothing, Int] = {
    def mkEnv() = new Console.Live with Clock.Live with System.Live
    program.provide(mkEnv()).foldM(
      err => putStrLn(s"Execution failed with: $err") *> ZIO.succeed(1),
      _ => ZIO.succeed(0)
    )
  }

  val programOld = for {
    promise <- Promise.make[Nothing,String]
    _ <- promise.succeed("Good bye cruel world!").delay(3.seconds).fork
    _ <- putStrLn("Hello World")
    value <- promise.await
    _ <- putStrLn(value)
  } yield ()

  val getEventDialog: ZIO[Console, IOException, Option[EventSummary]] = for {
    _ <- putStrLn("What event would you like to add?")
    ans <- getStrLn
    result <- ans match {
      case str:String if str.trim().isEmpty =>
        ZIO.succeed(None)
      case _ => ZIO.succeed(Option(EventSummary(ans)))
    }
  } yield result

  def reserveTicketsDialog(events:List[EventSummary]): ZIO[Console, IOException, List[EventSummary]] = for {
    event <- getEventDialog
    result <- event match {
      case None => ZIO.succeed(events)
      case Some(eventId) => reserveTicketsDialog(eventId::events)
    }
  } yield result

  val rootDialog: ZIO[Console with System, IOException, Unit] = for {
    _ <- putStrLn("What would you like to do?")
    _ <- putStrLn("[1] - Reserve tickets")
    _ <- putStrLn("[X] - Exit")
    answer <- getStrLn
    _ <- answer match {
      case "1" =>
        reserveTicketsDialog(List.empty).flatMap { events =>
          val text: String = events.mkString("[",",","]")
          for {
            nl <- lineSeparator
            _ <- putStrLn(s"Submitting reservation for events: $nl$text")
          } yield ()
        }
      case "X" | "x" => ZIO.succeed(())
      case _ => rootDialog
    }
  } yield ()

  val program = for {
    _ <- rootDialog
    _ <- putStrLn("All done. Goodbye!")
  } yield ()
}
