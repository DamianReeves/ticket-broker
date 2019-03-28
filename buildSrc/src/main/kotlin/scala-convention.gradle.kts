import gradle.kotlin.dsl.accessors._8dd18932e5b24cf57de00d1517f43cc7.testRuntime

plugins {
  scala
}

dependencies {
  implementation(scalalib("scala-library"))

  testImplementation("junit:junit:4.12")
  testImplementation(scalaDep("org.scalatest::scalatest:3.0.5"))
  testImplementation(scalaDep("org.scalacheck::scalacheck:1.14.0"))
  testImplementation(scalaDep("com.github.alexarchambault::scalacheck-shapeless_1.14:1.2.1"))
  // Need scala-xml at test runtime
  testRuntime(scalaDep("org.scala-lang.modules::scala-xml:1.1.0"))
}

tasks.withType<ScalaCompile> {
  scalaCompileOptions.additionalParameters = listOf(
    "-Xfatal-warnings",
    "-target:jvm-1.8",
    "-unchecked",
    "-deprecation",
    "-encoding", "UTF-8",
    "-Xfuture",
    "-Yno-adapted-args",
    "-Ywarn-dead-code",
    "-Ywarn-numeric-widen",
    "-Ywarn-value-discard",
    "-Ywarn-unused:imports,patvars,privates,locals,implicits",
    "-feature",
    "-language:implicitConversions",
    "-language:higherKinds",
    "-language:postfixOps",
    "-Ypartial-unification"
  )
}
