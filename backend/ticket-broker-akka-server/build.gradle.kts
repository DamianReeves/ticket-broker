plugins {
  scala
  `scala-convention`
}


dependencies {
  implementation(project(":ticket-broker-common"))
  implementation(scalaDep("com.typesafe.scala-logging::scala-logging:3.9.2"))
  implementation(scalaDep("org.scalaz::scalaz-zio-interop-cats".withVersion(project.zioVersion)))
  implementation(scalaDep("org.scalaz::scalaz-zio-interop-future".withVersion(project.zioVersion)))
  compile(scalaDep("com.typesafe.akka::akka-http".withVersion("10.1.8")))
  compile(scalaDep("com.typesafe.akka::akka-stream".withVersion(project.akkaVersion)))
  compile(scalaDep("com.typesafe.akka::akka-actor-typed".withVersion(project.akkaVersion)))
  compile(scalaDep("com.lightbend.akka::alpakka:1.0-M2"))
  compile(scalaDep("com.github.alexarchambault::case-app:1.2.0"))
  compile("ch.qos.logback:logback-classic:1.2.3")
}

