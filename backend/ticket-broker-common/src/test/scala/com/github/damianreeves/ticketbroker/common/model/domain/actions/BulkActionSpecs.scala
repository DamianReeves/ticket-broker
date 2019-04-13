package com.github.damianreeves.ticketbroker.common.model.domain.actions

import com.github.damianreeves.ticketbroker.common.model.domain.testing.AvroSupportFixture
import com.typesafe.scalalogging.LazyLogging
import org.apache.avro.Schema
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.prop.PropertyChecks
import org.scalatest.{FlatSpec, Matchers}

@RunWith(classOf[JUnitRunner])
class BulkActionSpecs extends FlatSpec with AvroSupportFixture with Matchers with PropertyChecks with LazyLogging  {

  behavior of "The BulkAction type"

  it should "support Avro schema generation" in {
    shouldGenerateAvroSchemaOfType[BulkAction](Schema.Type.UNION)
  }

}


