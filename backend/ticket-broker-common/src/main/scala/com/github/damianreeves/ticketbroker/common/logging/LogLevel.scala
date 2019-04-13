package com.github.damianreeves.ticketbroker.common.logging

sealed abstract class LogLevel(value:Int)

object LogLevel {
  final case object Error extends LogLevel(0)
  final case object Warning extends LogLevel(1)
  final case object Info extends  LogLevel(2)
  final case object Debug extends LogLevel(3)
  final case object Trace extends LogLevel(4)
}


