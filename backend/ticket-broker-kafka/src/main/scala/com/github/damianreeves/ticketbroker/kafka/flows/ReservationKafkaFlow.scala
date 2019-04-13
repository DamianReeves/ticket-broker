package com.github.damianreeves.ticketbroker.kafka.flows

import com.github.damianreeves.ticketbroker.common.model.domain.reservation.{Reservation, ReservationUrn}
import com.sksamuel.avro4s.kafka.GenericSerde
import org.apache.kafka.common.serialization.Serde
import org.apache.kafka.streams.Topology
import org.apache.kafka.streams.kstream.Printed
import org.apache.kafka.streams.scala.StreamsBuilder
import org.apache.kafka.streams.scala.ImplicitConversions._

trait ReservationKafkaFlow {

  implicit val reservationUrnSerde:Serde[ReservationUrn] = new GenericSerde[ReservationUrn]()
  implicit val reservationSerde:Serde[Reservation] = new GenericSerde[Reservation]()

  def configureStreams(builder:StreamsBuilder):StreamsBuilder = {
    val requestStream = builder.stream[ReservationUrn,Reservation]("queuing.ticket_broker.requests")
    requestStream.print(Printed.toSysOut[ReservationUrn,Reservation])
    requestStream
      .to("queuing.ticket_broker.responses")
    builder
  }

  def configureTopology(topology:Topology):Topology = {
    topology
  }
}
