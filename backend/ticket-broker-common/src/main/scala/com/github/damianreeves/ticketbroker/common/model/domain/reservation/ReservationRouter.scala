package com.github.damianreeves.ticketbroker.common.model.domain.reservation

import scalaz.zio.Task

trait ReservationRouter {
  val reservationRouter:ReservationRouter.Service
}

object ReservationRouter {

  final case class RoutingDecision(ticketMarket:Request, ticketStub:Request)

  trait Service {
    def route(request: Request):Task[RoutingDecision]
  }
}

