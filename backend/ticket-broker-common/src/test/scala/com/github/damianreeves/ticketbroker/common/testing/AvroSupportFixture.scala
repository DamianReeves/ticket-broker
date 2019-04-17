package com.github.damianreeves.ticketbroker.common.testing

import scala.reflect.ClassTag
import com.sksamuel.avro4s.{Decoder, Encoder, SchemaFor}
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

  def shouldSupportRountripEncodingAndDecoding[A](
    original:A,
    logFn: String => Unit = s => logger.info(s))(implicit
    encoder:Encoder[A],
    decoder:Decoder[A],
    schemaFor: SchemaFor[A]) = {
    val schema = schemaFor.schema
    val encoded = encoder.encode(original, schema)
    val actual = decoder.decode(encoded, schema)

    logFn(s"""The encoded instance did not match/meet the expectations
       |Encoded: $encoded
       |Original Instance: $original
       """)

    actual shouldBe original
  }
}
