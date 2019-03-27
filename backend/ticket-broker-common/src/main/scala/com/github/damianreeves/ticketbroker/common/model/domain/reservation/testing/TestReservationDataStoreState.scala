package com.github.damianreeves.ticketbroker.common.model.domain.reservation.testing

import com.github.damianreeves.ticketbroker.common.model.domain.reservation.{Request, ReservationRequestId}

final case class TestReservationDataStoreState(
  findRequests:List[ReservationRequestId],
  saveRequests:List[Request],
  data:Map[ReservationRequestId, Request]
) {

  def addFindRequest(id:ReservationRequestId):TestReservationDataStoreState =
    copy(findRequests = id::findRequests)

  def addSaveRequest(request:Request):TestReservationDataStoreState =
    copy(saveRequests = request::saveRequests)

  def doSaveRequest(request:Request):TestReservationDataStoreState =
    copy(data = data + (request.id -> request))
}


