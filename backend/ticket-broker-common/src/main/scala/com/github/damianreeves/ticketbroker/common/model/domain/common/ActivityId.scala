package com.github.damianreeves.ticketbroker.common.model.domain.common

sealed trait ActivityId
object ActivityId {
  case class ReservationId() extends ActivityId
}

