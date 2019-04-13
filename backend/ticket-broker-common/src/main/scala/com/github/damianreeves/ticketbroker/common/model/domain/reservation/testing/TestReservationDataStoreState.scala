package com.github.damianreeves.ticketbroker.common.model.domain.reservation.testing

import com.github.damianreeves.ticketbroker.common.model.domain.reservation.{Reservation, ReservationUrn}

final case class TestReservationDataStoreState(
  findRequests:List[ReservationUrn],
  saveRequests:List[Reservation],
  data:Map[ReservationUrn, Reservation]
) {

  def addFindRequest(urn:ReservationUrn):TestReservationDataStoreState =
    copy(findRequests = urn::findRequests)

  def addSaveRequest(reservation: Reservation):TestReservationDataStoreState =
    copy(saveRequests = reservation::saveRequests)

  def doSaveRequest(reservation: Reservation):TestReservationDataStoreState =
    copy(data = data + (reservation.urn -> reservation))
}


