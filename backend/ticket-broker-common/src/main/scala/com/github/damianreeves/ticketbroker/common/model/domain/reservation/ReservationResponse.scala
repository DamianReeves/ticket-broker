package com.github.damianreeves.ticketbroker.common.model.domain.reservation

import com.github.damianreeves.ticketbroker.common.model.domain.reservation.ReservationRequestId.BulkInitiateReservationRequestId

sealed trait ReservationResponse

object ReservationResponse {

  final case class BulkInitiateReservationResponse(id:BulkInitiateReservationRequestId)
    extends ReservationResponse
}
