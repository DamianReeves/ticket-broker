package com.github.damianreeves.ticketbroker.common.model.domain.events

object valuetypes {

  final case class EventId(value:String) extends AnyVal
  object EventId {

    implicit def toString(eventId:EventId):String = eventId.value
  }

}