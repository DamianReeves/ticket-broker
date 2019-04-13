package com.github.damianreeves.ticketbroker.common.model.domain.reservation

import java.util.UUID

import com.github.damianreeves.ticketbroker.common.model.domain.marketplace.MarketPlaceType

final case class ReservationUrn(uid:UUID, marketplace:MarketPlaceType) {
  def asUrn:String = s"urn:reservation:marketplace=$marketplace;uid=$uid"
  override def toString:String = asUrn
}
