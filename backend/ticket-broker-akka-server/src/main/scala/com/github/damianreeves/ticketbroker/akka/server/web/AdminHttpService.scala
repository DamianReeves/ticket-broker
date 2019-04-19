package com.github.damianreeves.ticketbroker.akka.server.web

import java.time.LocalDateTime

import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Route
import com.github.damianreeves.ticketbroker.akka.server.web.HttpService.ApiService
import javax.ws.rs.{GET, Path}

@Path("/admin")
class AdminHttpService extends ApiService("admin", "admin") {

  val postShutdown: Route = {
    path("shutdown") {
      post {
        complete(HttpEntity(
          ContentTypes.`application/json`,
          s"""{
             | "shutdownTime": ${LocalDateTime.now()}
             |}""".stripMargin)
        )
      }
    }
  }

  @GET
  def index: Route = pathEndOrSingleSlash {
    get {
      complete(HttpEntity(ContentTypes.`text/html(UTF-8)`,
        """<h1>Ticket Broker Admin</h1>"""))
    }
  }

  override val myRoutes: Route =
    index
}

object AdminHttpService {
  def apply(): AdminHttpService = new AdminHttpService()
}


