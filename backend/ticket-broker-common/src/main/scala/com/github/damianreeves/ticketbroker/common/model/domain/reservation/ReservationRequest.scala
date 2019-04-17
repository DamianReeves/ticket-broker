package com.github.damianreeves.ticketbroker.common.model.domain.reservation

import com.github.damianreeves.ticketbroker.common.model.domain.events.valuetypes.EventId

sealed trait ReservationRequest
object ReservationRequest {
  case class ReserveTickets(eventId:EventId, quantity: Long) extends ReservationRequest
}
