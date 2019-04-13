package com.github.damianreeves.ticketbroker.common.model.domain.reservation

import com.github.damianreeves.ticketbroker.common.model.domain.events.valuetypes.EventId

sealed trait Reservation {
  def eventId:EventId
}

object Reservation {
  case class ReservationDraft(eventId: EventId) extends Reservation
}


