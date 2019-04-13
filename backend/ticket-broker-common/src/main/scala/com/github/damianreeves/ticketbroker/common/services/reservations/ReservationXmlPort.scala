package com.github.damianreeves.ticketbroker.common.services.reservations

import scalaz.zio.{Task, ZIO}

import scala.xml.Elem

object ReservationXmlPort {
  trait Service {
    def reserveTickets(reservationXml:Elem):Task[Elem]
  }
}


trait ReservationXmlPort {
  def reservationPipeline:ReservationXmlPort.Service
}

object reservationSystem {
  def reserveTickets(reservationXml:Elem):ZIO[ReservationXmlPort, Throwable, Elem] =
    ZIO.accessM(_.reservationPipeline.reserveTickets(reservationXml))
}
