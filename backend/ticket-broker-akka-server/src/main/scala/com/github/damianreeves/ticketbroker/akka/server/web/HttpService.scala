package com.github.damianreeves.ticketbroker.akka.server.web

import akka.http.scaladsl.model._
import akka.http.scaladsl.server.{Directives, ExceptionHandler, Route}
import com.typesafe.scalalogging.LazyLogging
import StatusCodes._

trait HttpService extends Directives with LazyLogging  {
  val serviceName:String
  val servicePrefix:String = serviceName

  def myRoutes:Route

  final val route:Route = {
    handleExceptions(exceptionHandler) {
      Option(servicePrefix) match {
        case None => myRoutes
        case Some(text) if text.trim.isEmpty => myRoutes
        case Some(prefix) => pathPrefix(prefix) {
          myRoutes
        }
      }
    }
  }

  def exceptionHandler = ExceptionHandler {
    case error:NullPointerException =>
      extractUri { uri =>
        logger.error(s"A NullPointerException occurred while processing uri: $uri", error)
        complete(HttpResponse(InternalServerError, entity = "It's the billion dollar mistake!!!"))
      }
  }
}

object HttpService {
  abstract class ApiService(name:String, prefix:String = null) extends HttpService {
    override val serviceName: String = name
    override val servicePrefix: String = Option(prefix) match {
      case None => ""
      case Some(text) if text.trim.isEmpty => ""
      case Some(text) => text
    }
  }
}
