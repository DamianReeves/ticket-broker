plugins {
  scala
}

dependencies {
  implementation("org.scala-lang:scala-library:2.12.8")
  testImplementation("org.scalatest:scalatest_2.12:3.0.5")
  testImplementation("org.scalacheck:scalacheck_2.12:1.14.0")
  testImplementation("junit:junit:4.12")
  testRuntime ("org.scala-lang.modules:scala-xml_2.12:1.1.0")
}

