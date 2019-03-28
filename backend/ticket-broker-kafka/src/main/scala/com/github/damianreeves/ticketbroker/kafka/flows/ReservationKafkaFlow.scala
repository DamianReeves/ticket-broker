package com.github.damianreeves.ticketbroker.kafka.flows

import com.github.damianreeves.ticketbroker.common.model.domain.reservation.{Request, ReservationRequestId}
import com.sksamuel.avro4s.kafka.GenericSerde
import org.apache.kafka.common.serialization.Serde
import org.apache.kafka.streams.Topology
import org.apache.kafka.streams.kstream.Printed
import org.apache.kafka.streams.scala.StreamsBuilder
import org.apache.kafka.streams.scala.ImplicitConversions._

trait ReservationKafkaFlow {

  implicit val reservationRequestIdSerde:Serde[ReservationRequestId] = new GenericSerde[ReservationRequestId]()
  implicit val requestSerde:Serde[Request] = new GenericSerde[Request]()

  def configureStreams(builder:StreamsBuilder):StreamsBuilder = {
    val requestStream = builder.stream[ReservationRequestId,Request]("queuing.ticket_broker.requests")
    requestStream.print(Printed.toSysOut[ReservationRequestId,Request])
    requestStream
      .to("queuing.ticket_broker.responses")
    builder
  }

  def configureTopology(topology:Topology):Topology = {
    topology
  }
}
