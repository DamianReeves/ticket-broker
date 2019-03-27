import build.*
import org.gradle.api.Project

fun String.asScalaDependency() = Deps.asScalaDependency(this)
fun String.withVersion(version:String) = ("$this:$version")
fun String.withSpringBootVersion() = ("$this:${Deps.springBootVersion}")

fun Project.scalalib(name:String) = "org.scala-lang:$name:$scalaVersion"
fun Project.scalaDep(notation:String) = Deps.asScalaDependency(notation)

val Project.scalaBaseVersion get() = Deps.scalaBaseVersion
val Project.scalaVersion get() = Deps.scalaVersion
val Project.springBootVersion get() = Deps.springBootVersion
