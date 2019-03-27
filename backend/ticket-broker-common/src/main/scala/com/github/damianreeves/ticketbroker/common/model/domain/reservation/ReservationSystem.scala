package com.github.damianreeves.ticketbroker.common.model.domain.reservation

import com.github.damianreeves.ticketbroker.common.model.domain.reservation.Request.BulkInitiateReservation
import com.github.damianreeves.ticketbroker.common.model.domain.reservation.ReservationResponse.BulkInitiateReservationResponse
import scalaz.zio.Task

trait ReservationSystem {
  val reservationSystem:ReservationSystem.Service
}

object ReservationSystem {
  trait Service {
    def bulkSubmitReservations(request:BulkInitiateReservation):Task[BulkInitiateReservationResponse]
  }
}


