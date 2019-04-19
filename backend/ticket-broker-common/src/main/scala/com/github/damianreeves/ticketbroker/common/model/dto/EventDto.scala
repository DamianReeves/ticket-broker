package com.github.damianreeves.ticketbroker.common.model.dto


import java.time.Instant

import com.sksamuel.avro4s.{FromRecord, SchemaFor, ToRecord}

case class EventDto(title:String, startTime:Instant, endTime:Instant)
object EventDto {
  implicit val eventDtoSchemaFor:SchemaFor[EventDto] = SchemaFor[EventDto]
  implicit val eventDtoToRecord:ToRecord[EventDto] = ToRecord[EventDto]
  implicit val eventDtoFromRecord:FromRecord[EventDto] = FromRecord[EventDto]
}
