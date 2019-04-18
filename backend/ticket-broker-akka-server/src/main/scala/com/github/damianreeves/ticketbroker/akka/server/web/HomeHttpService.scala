package com.github.damianreeves.ticketbroker.akka.server.web

import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Route
import com.github.damianreeves.ticketbroker.akka.server.web.HttpService.ApiService

class HomeHttpService extends ApiService("home") {

  val index: Route = pathEndOrSingleSlash {
    get {
      complete(HttpEntity(ContentTypes.`text/html(UTF-8)`,
        """<h1>Ticket Broker</h1>"""))
    }
  }

  override def myRoutes: Route = index
}

object HomeHttpService {
  def apply(): HomeHttpService = new HomeHttpService()
}

