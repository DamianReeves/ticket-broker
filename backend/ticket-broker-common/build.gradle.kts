plugins {
  scala
  `scala-convention`
}

val scalaVersion:String by lazy { Deps.scalaVersion }
val scalaBaseVersion:String by  lazy { Deps.scalaBaseVersion }

dependencies {
  implementation("com.sksamuel.avro4s:avro4s-kafka_$scalaBaseVersion:2.0.4")
  implementation("com.typesafe.scala-logging:scala-logging_$scalaBaseVersion:3.9.2")
}

