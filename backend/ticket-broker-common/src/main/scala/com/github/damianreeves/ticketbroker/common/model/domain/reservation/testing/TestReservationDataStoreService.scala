package com.github.damianreeves.ticketbroker.common.model.domain.reservation.testing

import com.github.damianreeves.ticketbroker.common.model.domain.reservation._
import scalaz.zio.{Ref, Task}

final case class TestReservationDataStoreService(ref:Ref[TestReservationDataStoreState]) extends ReservationDataStore.Service {

  override def findReservation(urn: ReservationUrn): Task[Option[Reservation]] = for {
    _ <- ref.update(_.addFindRequest(urn))
    data <- ref.get.map(_.data)
    result <- Task.succeedLazy(data.get(urn))
  } yield result

  override def saveReservation(reservation: Reservation): Task[Unit] = for {
    _ <- ref.update(_.addSaveRequest(reservation))
    _ <- ref.update(_.doSaveRequest(reservation))
  } yield ()
}

