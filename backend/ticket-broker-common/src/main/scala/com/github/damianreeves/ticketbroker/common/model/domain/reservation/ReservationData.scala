package com.github.damianreeves.ticketbroker.common.model.domain.reservation

import com.github.damianreeves.ticketbroker.common.model.domain.events.valuetypes.EventId

sealed trait ReservationData

object ReservationData {
  final case class BulkReservationData(reservations:List[ReservationData] )
  final case class InitiateReservationData(eventId:EventId) extends ReservationData
}
