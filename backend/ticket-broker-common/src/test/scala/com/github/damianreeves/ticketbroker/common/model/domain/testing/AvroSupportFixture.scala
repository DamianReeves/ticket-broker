package com.github.damianreeves.ticketbroker.common.model.domain.testing

import com.sksamuel.avro4s.SchemaFor
import com.typesafe.scalalogging.LazyLogging
import org.apache.avro.Schema
import org.scalatest.{Assertion, Matchers, TestSuite}

trait AvroSupportFixture { self: TestSuite with Matchers with LazyLogging =>
  def shouldGenerateAvroSchemaOfType[A](schemaType:Schema.Type)(implicit schemaFor: SchemaFor[A]): Assertion = {

    val schema = schemaFor.schema

    logger.info(
      s"""=======================================================================================
         |Schema:
         |${schema.toString(true)}
         |=======================================================================================
       """.stripMargin)

    schema.getType shouldBe schemaType
  }
}
