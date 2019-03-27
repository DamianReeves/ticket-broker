package com.github.damianreeves.ticketbroker.common.model.domain.reservation

sealed trait ReservationRequestId {
  def urn:String
}

object ReservationRequestId {

  final case class BulkInitiateReservationRequestId(value:String) extends ReservationRequestId {
    def urn:String = s"urn:request:reservation:bulk-initiate:$value"
  }

  final case class BulkUpdateReservationRequestId(value:String) extends ReservationRequestId {
    def urn:String = s"urn:request:reservation:bulk-update:$value"
  }

  final case class InitiateReservationRequestId(value:String) extends ReservationRequestId {
    def urn:String = s"urn:request:reservation:initiate:$value"
  }


  final case class UpdateReservationRequestId(value:String) extends ReservationRequestId {
    def urn:String = s"urn:request:reservation:initiate:$value"
  }
}
