plugins {
  scala
  `scala-convention`
}

dependencies {
  implementation("com.sksamuel.avro4s::avro4s-kafka:2.0.4".asScalaDependency())
  implementation("com.typesafe.scala-logging::scala-logging:3.9.2".asScalaDependency())
}
