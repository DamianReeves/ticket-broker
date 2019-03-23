plugins {
  scala
  `scala-convention`
}


dependencies {
  implementation(project(":ticket-broker-common"))
  implementation(scalaDep("com.typesafe.scala-logging::scala-logging:3.9.2"))
}

