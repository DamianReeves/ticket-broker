package com.github.damianreeves.ticketbroker.common.model.dto

import com.github.damianreeves.ticketbroker.common.testing.{AvroSupportFixture, BddSpec}
import com.typesafe.scalalogging.LazyLogging
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class EventQuerySpecs extends BddSpec with AvroSupportFixture with LazyLogging {
  import org.scalacheck.ScalacheckShapeless._

  behavior of "Avro support for the EventQuery type"

  it should "support round-trip encoding and decoding to Avro" in {
    forAll { query:EventQuery =>
      shouldSupportRountripEncodingAndDecoding(query)
    }
  }
}
