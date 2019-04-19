package com.github.damianreeves.ticketbroker.akka.server.web

import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Route
import com.github.damianreeves.ticketbroker.akka.server.web.HttpService.ApiService
import javax.ws.rs.{GET, Path}

@Path(value="/event")
class EventHttpService extends ApiService("event", "event") {

  @GET
  def index:Route = {
    get {
      complete(HttpEntity(ContentTypes.`text/html(UTF-8)`,
        """<h1>Ticket Broker - Events</h1>"""))
    }
  }

  val myRoutes:Route = index
}

object EventHttpService {
  def apply(): EventHttpService = new EventHttpService
}
