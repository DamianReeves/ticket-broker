package com.github.damianreeves.ticketbroker.common.model.domain.events

import com.sksamuel.avro4s.{FromRecord, SchemaFor, ToRecord}

object valuetypes {

  final case class EventId(value:String) extends AnyVal
  object EventId {
    implicit val eventIdschemaFor = SchemaFor[EventId]
    implicit val eventIdFromRecord = FromRecord[EventId]
    implicit val eventIdToRecord = ToRecord[EventId]
    implicit def toString(eventId:EventId):String = eventId.value
  }

  final case class EventSummary(name:String) {
    override def toString: String = name
  }

  case class EventTitle(value:String) extends AnyVal
}

