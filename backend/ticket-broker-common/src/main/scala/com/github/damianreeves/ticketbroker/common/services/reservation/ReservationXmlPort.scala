package com.github.damianreeves.ticketbroker.common.services.reservation

import scalaz.zio.Task

import scala.xml.Elem

object ReservationXmlPort {
  trait Service {
    def reserveTickets(reservationXml:Elem):Task[Elem]
  }
}

trait ReservationXmlPort {
  def reservationPipeline:ReservationXmlPort.Service
}
