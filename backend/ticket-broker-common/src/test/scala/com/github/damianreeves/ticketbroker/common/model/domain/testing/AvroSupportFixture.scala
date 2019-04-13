package com.github.damianreeves.ticketbroker.common.model.domain.testing

import scala.reflect.ClassTag
import com.sksamuel.avro4s.{Encoder, SchemaFor}
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

  def typeShouldSupportAvroEncoding[A](implicit classTag:ClassTag[A], encoder:Encoder[A] = null) = {
    assert(encoder != null, s"An encoder was not available for type ${classTag.runtimeClass.getName}.")
  }

  def shouldEncodeToValueMatching[A](instance:A, matcher: (A, AnyRef) => Boolean)(implicit
    encoder:Encoder[A],
    schemaFor:SchemaFor[A]) = {
    val schema = schemaFor.schema
    val encoded = encoder.encode(instance, schema)
    if(matcher(instance, encoded)){
      succeed
    } else {
      fail(s"""The encoded instance did not match/meet the expectations
              |Encoded: $encoded
              |Original Instance: $instance
       """.stripMargin)
    }

  }
}
