package com.github.damianreeves.ticketbroker.common.model.domain.reservation

import com.github.damianreeves.ticketbroker.common.model.domain.events.valuetypes.EventId

sealed trait Reservation {
  def urn:ReservationUrn
  def eventId:EventId
}

object Reservation {
  case class ReservationDraft(urn:ReservationUrn, eventId: EventId) extends Reservation
}




