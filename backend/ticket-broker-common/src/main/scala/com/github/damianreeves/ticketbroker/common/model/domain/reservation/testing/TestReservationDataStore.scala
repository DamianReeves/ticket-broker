package com.github.damianreeves.ticketbroker.common.model.domain.reservation.testing

import com.github.damianreeves.ticketbroker.common.model.domain.reservation.ReservationDataStore
import scalaz.zio.Ref

object TestReservationDataStore {
  def apply(ref:Ref[TestReservationDataStoreState]):ReservationDataStore =
    new ReservationDataStore {
      override val dataStore: ReservationDataStore.Service =
        TestReservationDataStoreService(ref)
    }
}
