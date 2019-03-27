plugins {
  scala
  `scala-convention`
}

dependencies {
  implementation(project(":ticket-broker-common"))
  implementation(scalaDep("com.sksamuel.avro4s::avro4s-kafka:2.0.4"))
  implementation(scalaDep("com.typesafe.scala-logging::scala-logging:3.9.2"))
  implementation(scalaDep("org.scalaz::scalaz-zio-interop-cats:0.16"))
  implementation(scalaDep("org.scalaz::scalaz-zio-interop-monix:0.6.0"))
  implementation(scalaDep("io.monix::monix:3.0.0-RC2"))
}
