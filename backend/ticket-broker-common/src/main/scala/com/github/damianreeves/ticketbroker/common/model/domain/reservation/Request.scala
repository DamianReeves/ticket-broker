package com.github.damianreeves.ticketbroker.common.model.domain.reservation

import com.github.damianreeves.ticketbroker.common.model.domain.reservation.ReservationRequestId.{BulkInitiateReservationRequestId, BulkUpdateReservationRequestId, InitiateReservationRequestId, UpdateReservationRequestId}

sealed abstract class Request(id:ReservationRequestId) {
  def urn:String = id.urn
}

object Request {

  final case class BulkInitiateReservation(id:BulkInitiateReservationRequestId) extends Request(id)
  final case class BulkUpdateReservation(id:BulkUpdateReservationRequestId) extends Request(id)
  final case class InitiateReservation(id:InitiateReservationRequestId) extends Request(id)
  final case class UpdateReservation(id:UpdateReservationRequestId) extends Request(id)

  implicit class RequestOps(val self:Request) extends AnyVal {

    def id:ReservationRequestId = self match {
      case request:BulkInitiateReservation => request.id
      case request:BulkUpdateReservation => request.id
      case request:InitiateReservation => request.id
      case request:UpdateReservation => request.id
    }
  }
}


