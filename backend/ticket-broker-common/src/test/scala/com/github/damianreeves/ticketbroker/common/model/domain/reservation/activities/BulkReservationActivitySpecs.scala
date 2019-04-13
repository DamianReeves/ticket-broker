package com.github.damianreeves.ticketbroker.common.model.domain.reservation.activities

import com.typesafe.scalalogging.LazyLogging
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.prop.PropertyChecks
import org.scalatest.{FlatSpec, Matchers}

@RunWith(classOf[JUnitRunner])
class BulkReservationActivitySpecs extends FlatSpec with Matchers with PropertyChecks with LazyLogging  {
  behavior of "The BulkReservationActivity type"

  it should "support Avro schema generation" in {
//    val schema = SchemaFor[BulkReservationActivity].schema
//
//
//    schema.getType shouldBe Schema.Type.UNION
  }
}
