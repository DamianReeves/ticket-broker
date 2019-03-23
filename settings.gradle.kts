rootProject.name = "ticket-broker-app"

include (
  ":ticket-broker-common",
  ":ticket-broker-server"
)

project(":ticket-broker-common").projectDir =
  file("backend/ticket-broker-common")

project(":ticket-broker-server").projectDir =
  file("backend/ticket-broker-server")
