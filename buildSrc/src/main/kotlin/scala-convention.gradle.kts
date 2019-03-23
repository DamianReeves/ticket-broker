plugins {
  scala
}

val scalaVersion:String by lazy { Deps.scalaVersion }
val scalaBaseVersion:String by  lazy { Deps.scalaBaseVersion }

dependencies {
  // Use Scala 2.11 in our library project
  implementation("org.scala-lang:scala-library:$scalaVersion")

  testImplementation("junit:junit:4.12")
  testImplementation("org.scalatest:scalatest_$scalaBaseVersion:3.0.5")
  testImplementation("org.scalacheck:scalacheck_$scalaBaseVersion:1.14.0")
  // Need scala-xml at test runtime
  testRuntime("org.scala-lang.modules:scala-xml_$scalaBaseVersion:1.1.0")
}

