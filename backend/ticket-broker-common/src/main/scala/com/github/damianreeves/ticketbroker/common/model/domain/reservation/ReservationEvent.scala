package com.github.damianreeves.ticketbroker.common.model.domain.reservation

import com.github.damianreeves.ticketbroker.common.model.domain.reservation.ReservationProcessId.{InitiateReservationId, InitiateBulkReservationsId}

sealed trait ReservationEvent

object ReservationEvent {

  final case class ReservationsInitiated(id:InitiateBulkReservationsId, reservations:List[ReservationData])
    extends ReservationEvent

  final case class ReservationInitiated(id:InitiateReservationId)
}



