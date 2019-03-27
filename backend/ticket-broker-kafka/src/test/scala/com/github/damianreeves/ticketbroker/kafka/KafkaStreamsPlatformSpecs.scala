package com.github.damianreeves.ticketbroker.kafka

import java.util.Properties

import com.github.damianreeves.ticketbroker.common.filesystem.FileSystemOps
import com.github.damianreeves.ticketbroker.kafka.KafkaStreamsPlatform.Live
import org.apache.kafka.streams.{StreamsConfig, Topology}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FlatSpec, Matchers}

@RunWith(classOf[JUnitRunner])
class KafkaStreamsPlatformSpecs extends FlatSpec with Matchers {

  trait TestFixture extends FileSystemOps {

    def topology:Topology = new Topology

    val config:Properties = {
      val props = new Properties()
      props.setProperty(StreamsConfig.APPLICATION_ID_CONFIG, "kafka-streams-platform")
      props.setProperty(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
      props.setProperty(StreamsConfig.STATE_DIR_CONFIG,
        tempFolderPath.resolve("testing")
                      .resolve("kafka-streams")
                      .toAbsolutePath.toString
      )
      props
    }
  }


  behavior of "A Live KafkaStreamsPlatform instance"

  it should "support the creation of a KafkaStreamsFacade given a topology and config properties" in new TestFixture  {
    val facade = Live.kafkaStreamsPlatform.createKafkaStreams(topology, config)
    facade should not be null
  }
}
