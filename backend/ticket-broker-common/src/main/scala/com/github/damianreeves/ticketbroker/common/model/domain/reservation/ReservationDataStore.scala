package com.github.damianreeves.ticketbroker.common.model.domain.reservation

import scalaz.zio.Task

trait ReservationDataStore {
  val dataStore:ReservationDataStore.Service
}

object ReservationDataStore {
  trait Service {
    def findReservation(urn:ReservationUrn):Task[Option[Reservation]]
    def saveReservation(reservation:Reservation):Task[Unit]
  }
}

