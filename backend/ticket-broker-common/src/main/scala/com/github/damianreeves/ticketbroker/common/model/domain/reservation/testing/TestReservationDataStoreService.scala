package com.github.damianreeves.ticketbroker.common.model.domain.reservation.testing

import com.github.damianreeves.ticketbroker.common.model.domain.reservation.{Request, ReservationDataStore, ReservationRequestId}
import scalaz.zio.{Ref, Task}

final case class TestReservationDataStoreService(ref:Ref[TestReservationDataStoreState]) extends ReservationDataStore.Service {

  override def findRequest(id: ReservationRequestId): Task[Option[Request]] = for {
    _ <- ref.update(_.addFindRequest(id))
    data <- ref.get.map(_.data)
    result <- Task.succeedLazy(data.get(id))
  } yield result

  override def saveRequest(request: Request): Task[Unit] = for {
    _ <- ref.update(_.addSaveRequest(request))
    _ <- ref.update(_.doSaveRequest(request))
  } yield ()
}

