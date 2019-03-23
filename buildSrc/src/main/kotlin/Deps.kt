import org.gradle.api.Project

val Project.scalaBaseVersion get() = Deps.scalaBaseVersion
val Project.scalaVersion get() = Deps.scalaVersion
fun String.asScalaDependency() = Deps.asScalaDependency(this)

object Deps {

  const val scalaBaseVersion = "2.12"
  const val scalaVersion =  "$scalaBaseVersion.8"
  const val springBootVersion = "2.1.3.RELEASE"
  const val springKafkaVersion="2.2.0.RELEASE"
  const val kafkaClientsVersion="2.1.0"
  const val kafkaVersion="2.1.0"

  private val needsScalaVersion = """:{3}([a-zA-Z0-9._+-]+)""".toRegex()
  private val needsScalaBaseVersion = """:{2}([a-zA-Z0-9._+-]+)""".toRegex()

  fun asScalaDependency(notation:String):String =
    notation
      .let { input ->
        needsScalaVersion.replace(input) { module ->
          module.groups[1]?.let {":${it.value}_$scalaVersion"} ?: module.value
        }
      }
      .let {  input ->
        needsScalaBaseVersion.replace(input) { module ->
          module.groups[1]?.let {":${it.value}_$scalaBaseVersion"} ?: module.value
        }
      }
}
