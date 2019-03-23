package com.github.damianreeves.ticketbroker.common.model.domain.reservation

sealed trait Request

object Request {

  final case class BulkInitiateReservation(id:String) extends Request
  final case class InitiateReservation() extends Request
}


