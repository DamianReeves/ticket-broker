package com.github.damianreeves.ticketbroker.common.model.domain.reservation

sealed trait ReservationProcessId

object ReservationProcessId {
  final case class InitiateBulkReservationsId(value:String) extends ReservationProcessId
  final case class InitiateReservationId(value:String) extends ReservationProcessId
}


