pluginManagement {
  repositories {
    mavenCentral()
    gradlePluginPortal()
    jcenter()
  }
}

rootProject.name = "ticket-broker-app"

include ("ticket-broker-common")
include("ticket-broker-kafka")
include("ticket-broker-server")
include("ticket-broker-akka-server")
include("ticket-broker-spring-server")
include("ticket-broker-vertx-server")
include("ticket-broker-ktor-server")

include("ticket-broker-console")

project(":ticket-broker-common").projectDir = file("backend/ticket-broker-common")
project(":ticket-broker-kafka").projectDir = file("backend/ticket-broker-kafka")

project(":ticket-broker-server").projectDir = file("backend/ticket-broker-server")
project(":ticket-broker-akka-server").projectDir = file("backend/ticket-broker-akka-server")
project(":ticket-broker-spring-server").projectDir = file("backend/ticket-broker-spring-server")
project(":ticket-broker-vertx-server").projectDir = file("backend/ticket-broker-vertx-server")
project(":ticket-broker-ktor-server").projectDir = file("backend/ticket-broker-ktor-server")

project(":ticket-broker-console").projectDir = file("frontend/ticket-broker-console")
