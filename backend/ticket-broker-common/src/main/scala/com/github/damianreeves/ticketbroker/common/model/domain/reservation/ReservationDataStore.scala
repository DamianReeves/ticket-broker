package com.github.damianreeves.ticketbroker.common.model.domain.reservation

import scalaz.zio.Task

trait ReservationDataStore {
  val dataStore:ReservationDataStore.Service
}

object ReservationDataStore {
  trait Service {
    def findRequest(id:ReservationRequestId):Task[Option[Request]]
    def saveRequest(request:Request):Task[Unit]
  }
}

