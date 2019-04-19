package com.github.damianreeves.ticketbroker.akka.server.web

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.{Directives, Route}

trait SwaggerUISite extends Directives {
  val route: Route = (get & pathPrefix("swagger")){
    (pathEndOrSingleSlash & redirectToTrailingSlashIfMissing(StatusCodes.TemporaryRedirect)) {
      getFromResource("swagger/index.html")
    } ~ {
      getFromResourceDirectory("swagger")
    }
  }
}

object SwaggerUISite extends SwaggerUISite
