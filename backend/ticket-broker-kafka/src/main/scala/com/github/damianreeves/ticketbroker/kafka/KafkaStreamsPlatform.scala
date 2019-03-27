package com.github.damianreeves.ticketbroker.kafka

import java.util.Properties

import org.apache.kafka.streams.{KafkaStreams, Topology}
import scalaz.zio.Task

trait KafkaStreamsPlatform {
  val kafkaStreamsPlatform:KafkaStreamsPlatform.Service
}

object KafkaStreamsPlatform {
  trait Service {

    def createKafkaStreams(topology:Topology, config:Properties):Task[KafkaStreamsFacade]
    def start(streams:KafkaStreamsFacade):Task[KafkaStreamsResource]
    def stop(streamsResource: KafkaStreamsResource):Task[Unit]
  }

  trait Live extends KafkaStreamsPlatform {
    override val kafkaStreamsPlatform: Service = new Service {
      override def createKafkaStreams(topology: Topology, config: Properties): Task[KafkaStreamsFacade] = Task {
        val kafkaStreams = new KafkaStreams(topology, config)
        KafkaStreamsFacade(kafkaStreams)
      }

      override def start(streams: KafkaStreamsFacade): Task[KafkaStreamsResource] = Task {
        val resource:KafkaStreamsResource = streams
        streams.start()
        resource
      }

      override def stop(streamsResource: KafkaStreamsResource): Task[Unit] = Task {
        streamsResource.close()
      }
    }
  }

  object Live extends Live
}
