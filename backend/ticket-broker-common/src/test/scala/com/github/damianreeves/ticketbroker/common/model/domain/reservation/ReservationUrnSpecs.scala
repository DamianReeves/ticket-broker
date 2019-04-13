package com.github.damianreeves.ticketbroker.common.model.domain.reservation

import com.github.damianreeves.ticketbroker.common.model.domain.testing.AvroSupportFixture
import com.typesafe.scalalogging.LazyLogging
import org.apache.avro.Schema
import org.junit.runner.RunWith
import org.scalatest.prop.PropertyChecks
import org.scalatest.{FlatSpec, Matchers}
import org.scalacheck.ScalacheckShapeless._
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ReservationUrnSpecs extends FlatSpec with AvroSupportFixture with Matchers with PropertyChecks with LazyLogging {
  behavior of "The ReservationUrn type"

  it should "support Avro schema generation" in {
    shouldGenerateAvroSchemaOfType[ReservationUrn](Schema.Type.RECORD)
  }

  it should "support round-trip encoding and decoding to Avro" in {
    forAll { urn:ReservationUrn =>
      shouldSupportRountripEncodingAndDecoding(urn)
    }
  }
}
