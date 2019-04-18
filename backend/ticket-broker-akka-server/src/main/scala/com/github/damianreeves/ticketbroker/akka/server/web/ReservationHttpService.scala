package com.github.damianreeves.ticketbroker.akka.server.web

import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Route
import com.github.damianreeves.ticketbroker.akka.server.web.HttpService.ApiService

class ReservationHttpService extends ApiService("reservation", "reservation") {
  val index: Route = pathEndOrSingleSlash {
    get {
      complete(HttpEntity(ContentTypes.`text/html(UTF-8)`,
        """<h1>Ticket Broker - Reservations</h1>"""))
    }
  }
  override def myRoutes: Route = index
}

object ReservationHttpService {
  def apply(): ReservationHttpService = new ReservationHttpService()
}
