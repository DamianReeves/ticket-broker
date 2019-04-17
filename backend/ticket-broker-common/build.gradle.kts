plugins {
  scala
  `scala-convention`
}

dependencies {
  compile(scalaDep("org.scalaz::scalaz-zio".withVersion(project.zioVersion)))
  compile(scalaDep("com.lightbend.akka::alpakka:1.0-M2"))
  compile(scalaDep("com.sksamuel.avro4s::avro4s-core".withVersion(project.avro4sVersion )))
  compile(scalaDep("com.typesafe.scala-logging::scala-logging:3.9.2"))
  compile(scalaDep("com.github.pureconfig::pureconfig:0.10.2"))
  compile("org.rocksdb:rocksdbjni:5.17.2")
  
  implementation(scalaDep("org.scalaz::scalaz-zio-interop-cats".withVersion(project.zioVersion)))
  implementation(scalaDep("org.scalaz::scalaz-zio-interop-future".withVersion(project.zioVersion)))
  testImplementation("ch.qos.logback:logback-classic:1.2.3")
}
