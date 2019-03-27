      package com.github.damianreeves.ticketbroker.kafka

import org.apache.kafka.streams.KafkaStreams

sealed trait KafkaStreamsFacade {
  def start():Unit
  def close():Unit
  def cleanUp():Unit
}

object KafkaStreamsFacade {

  implicit def toKafkaStreamsResource(facade:KafkaStreamsFacade):KafkaStreamsResource =
    KafkaStreamsResource(() => facade.close())

  final case class KafkaStreamsBackedFacade(kafkaStreams:KafkaStreams) extends KafkaStreamsFacade {
    override def start(): Unit = kafkaStreams.start()

    override def close(): Unit = kafkaStreams.close()

    override def cleanUp(): Unit = kafkaStreams.cleanUp()
  }

  final case class AnonymousFacade(startFn:() => Unit, closeFn:() => Unit, cleanUpFn:() => Unit) extends KafkaStreamsFacade {
    override def start(): Unit = startFn()

    override def close(): Unit = closeFn()

    override def cleanUp(): Unit = cleanUpFn()
  }

  def apply(kafkaStreams: KafkaStreams):KafkaStreamsFacade =
    KafkaStreamsBackedFacade(kafkaStreams)

  def apply(startFn:() => Unit, closeFn:() => Unit, cleanUpFn:() => Unit):KafkaStreamsFacade =
    anonymous(
      startFn = startFn,
      closeFn = closeFn,
      cleanUpFn = cleanUpFn
    )


  def anonymous(startFn:() => Unit = () => (), closeFn:() => Unit = () => (), cleanUpFn:() => Unit = () => ()):AnonymousFacade =
    AnonymousFacade(
      startFn = startFn,
      closeFn = closeFn,
      cleanUpFn = cleanUpFn
    )
}

