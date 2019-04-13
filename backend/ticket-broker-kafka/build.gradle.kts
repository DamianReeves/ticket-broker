plugins {
  scala
  `scala-convention`
}

dependencies {

  implementation(project(":ticket-broker-common"))
  implementation(scalaDep("com.sksamuel.avro4s::avro4s-kafka:2.0.4"))
  implementation(scalaDep("com.typesafe.scala-logging::scala-logging:3.9.2"))
  implementation(scalaDep("org.scalaz::scalaz-zio".withVersion(project.zioVersion)))
  implementation(scalaDep("org.apache.kafka::kafka-streams-scala:2.1.0"))
  implementation(scalaDep("io.monix::monix-kafka-1x:1.0.0-RC2"))

  testImplementation("ch.qos.logback:logback-classic:1.2.3")
  testImplementation(scalaDep("org.apache.kafka:kafka-streams-test-utils:2.1.0"))
}
