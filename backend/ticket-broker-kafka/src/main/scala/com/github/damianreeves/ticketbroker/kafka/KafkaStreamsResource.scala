package com.github.damianreeves.ticketbroker.kafka

trait KafkaStreamsResource extends AutoCloseable

object KafkaStreamsResource {

  def apply(closeFn:() => Unit):KafkaStreamsResource = new KafkaStreamsResource {
    override def close(): Unit = closeFn()
  }
}




