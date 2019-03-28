package com.github.damianreeves.ticketbroker.common.model.domain.reservation

import com.sksamuel.avro4s.SchemaFor
import com.typesafe.scalalogging.LazyLogging
import org.apache.avro.Schema
import org.junit.runner.RunWith
import org.scalatest.prop.PropertyChecks
import org.scalatest.{FlatSpec, Matchers}
//import org.scalacheck.ScalacheckShapeless._
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class BulkReservationActivityIdSpecs extends FlatSpec with Matchers with PropertyChecks with LazyLogging {
  behavior of "The BulkReservationActivityId type"

  it should "support Avro schema generation" in {
    val schema = SchemaFor[BulkReservationActivityId].schema

    schema.getType shouldBe Schema.Type.UNION
    logger.info(s"Schema: ${schema.toString(true)}")
  }
}
