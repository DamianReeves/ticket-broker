plugins {
  scala
  `scala-convention`
}


dependencies {
  implementation(project(":ticket-broker-common"))
  implementation(scalaDep("com.typesafe.scala-logging::scala-logging:3.9.2"))
  implementation(scalaDep("org.scalaz::scalaz-zio-interop-cats".withVersion(project.zioVersion)))
  implementation(scalaDep("org.scalaz::scalaz-zio-interop-future".withVersion(project.zioVersion)))
}

