plugins {
  scala
}

dependencies {
  implementation(scalalib("scala-library"))

  testImplementation("junit:junit:4.12")
  testImplementation(scalaDep("org.scalatest::scalatest:3.0.5"))
  testImplementation(scalaDep("org.scalacheck::scalacheck:1.14.0"))
  // Need scala-xml at test runtime
  testRuntime(scalaDep("org.scala-lang.modules::scala-xml:1.1.0"))
}

