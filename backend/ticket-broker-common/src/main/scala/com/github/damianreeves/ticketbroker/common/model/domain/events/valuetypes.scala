package com.github.damianreeves.ticketbroker.common.model.domain.events

object valuetypes {

  final case class EventId(value:String) extends AnyVal
  object EventId {
    implicit def toString(eventId:EventId):String = eventId.value
  }

  final case class EventSummary(name:String) {
    override def toString: String = name
  }

  case class EventTitle(value:String) extends AnyVal
  object EventTitle {
    def create(title:String): EventTitle = EventTitle(title)
  }
}

