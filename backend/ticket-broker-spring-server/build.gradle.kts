plugins {
  scala
  `scala-convention`
  id("org.springframework.boot") version Deps.springBootVersion
}

apply(plugin="io.spring.dependency-management")

dependencies {
  implementation(project(":ticket-broker-common"))
  implementation(scalaDep("com.typesafe.scala-logging::scala-logging:3.9.2"))
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("org.springframework.boot:spring-boot-starter-actuator")
}

tasks.bootJar {
  mainClassName = "com.github.damianreeves.ticketbroker.spring.server.SpringTicketBrokerServer"
}

tasks.bootRun {
  main = "com.github.damianreeves.ticketbroker.spring.server.SpringTicketBrokerServer"
  args("--spring.profiles.active=local")
}
