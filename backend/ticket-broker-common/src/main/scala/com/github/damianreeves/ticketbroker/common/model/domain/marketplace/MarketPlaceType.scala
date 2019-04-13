package com.github.damianreeves.ticketbroker.common.model.domain.marketplace

sealed trait MarketPlaceType
object MarketPlaceType {
  case object StubNub extends MarketPlaceType
  case object TicketPortal extends MarketPlaceType
}
