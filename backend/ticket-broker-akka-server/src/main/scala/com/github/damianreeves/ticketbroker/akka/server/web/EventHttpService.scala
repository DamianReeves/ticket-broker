package com.github.damianreeves.ticketbroker.akka.server.web

import java.time.{LocalDateTime, ZoneId, ZoneOffset, ZonedDateTime}

import akka.actor.ActorSystem
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Route
import com.github.damianreeves.ticketbroker.akka.server.web.HttpService.ApiService
import com.github.damianreeves.ticketbroker.common.model.domain.events.valuetypes.EventTitle
import com.github.damianreeves.ticketbroker.common.model.dto.EventDto
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.{Operation, Parameter}
import io.swagger.v3.oas.annotations.media.{Content, Schema}
import io.swagger.v3.oas.annotations.responses.ApiResponse
import javax.ws.rs.{GET, Path}
import de.heikoseeberger.akkahttpjackson.JacksonSupport._
import HttpService.defaultObjectMapper

import scala.concurrent.ExecutionContextExecutor

@Path(value="/event")
class EventHttpService(implicit system:ActorSystem) extends ApiService("event", "event") {
  implicit val ec: ExecutionContextExecutor = system.dispatcher

  @GET
  @Operation(summary = "Event index page", description = "Event index page",
    responses = Array(
      new ApiResponse(responseCode = "200", description = "Event Page",
        content = Array(new Content(schema = new Schema (implementation = classOf[String]))))
    ))
  def index:Route = pathEndOrSingleSlash {
    get {
      complete(HttpEntity(ContentTypes.`text/html(UTF-8)`,
        """<h1>Ticket Broker - Events</h1>"""))
    }
  }

  @GET
  @Operation(summary = "Get an event by the event ID", description = "Get an event by the event ID",
    parameters = Array(new Parameter(name = "name", in = ParameterIn.PATH, description = "event ID")),
    responses = Array(
      new ApiResponse(responseCode = "200", description = "Event",
        content = Array(new Content(schema = new Schema (implementation = classOf[EventDto]))))
    ))
  def getById: Route = path(Segment) { id =>
    get {
      logger.info(s"Route hit => getById($id)")
      val event = EventDto (
        EventTitle("New Years Eve"),
        LocalDateTime.of(2019,12,31,20,0,0).toInstant(ZoneOffset.UTC),
        ZonedDateTime.of(LocalDateTime.of(2020,1,1,0,30,0), ZoneId.of("UTC"))
      )
      complete(event)
    }
  }

  val myRoutes:Route = getById ~ index
}

object EventHttpService {
  def apply()(implicit system:ActorSystem): EventHttpService = new EventHttpService
}
