package com.github.damianreeves.ticketbroker.common.services.reservation.system

import com.github.damianreeves.ticketbroker.common.services.reservation.ReservationXmlPort
import scalaz.zio.ZIO

import scala.xml.Elem

trait ReservationSystemSyntax {
  def reserveTickets(reservationXml:Elem):ZIO[ReservationXmlPort, Throwable, Elem] =
    ZIO.accessM(_.reservationPipeline.reserveTickets(reservationXml))
}
