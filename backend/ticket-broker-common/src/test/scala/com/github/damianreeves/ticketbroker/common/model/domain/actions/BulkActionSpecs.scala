package com.github.damianreeves.ticketbroker.common.model.domain.actions

import com.github.damianreeves.ticketbroker.common.testing.{AvroSupportFixture, BddSpec}
import com.typesafe.scalalogging.LazyLogging
import org.apache.avro.Schema
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class BulkActionSpecs extends BddSpec with AvroSupportFixture with LazyLogging {

  behavior of "The BulkAction type"

  it should "support Avro schema generation" in {
    shouldGenerateAvroSchemaOfType[BulkAction](Schema.Type.UNION)
  }

}


