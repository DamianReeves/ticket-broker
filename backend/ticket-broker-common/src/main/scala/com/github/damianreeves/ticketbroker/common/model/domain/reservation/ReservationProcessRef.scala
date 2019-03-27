package com.github.damianreeves.ticketbroker.common.model.domain.reservation

import com.github.damianreeves.ticketbroker.common.model.domain.reservation.ReservationProcessId.InitiateBulkReservationsId

sealed trait ReservationProcessRef[Id<:ReservationProcessId] extends ReservationProcessRefLike {
  def id:Id
  override def processId: ReservationProcessId = id
}


object ReservationProcessRef {

  final case class InitiateBulkReservations(id:InitiateBulkReservationsId)
    extends ReservationProcessRef[InitiateBulkReservationsId]
}
