plugins {
  scala
  `scala-convention`
  id("org.springframework.boot") version (build.Deps.springBootVersion)
}

apply(plugin="io.spring.dependency-management")

dependencies {
  implementation(project(":ticket-broker-common"))
  implementation(scalaDep("com.sksamuel.avro4s::avro4s-kafka:2.0.4"))
  implementation(scalaDep("com.typesafe.scala-logging::scala-logging:3.9.2"))
  implementation(scalaDep("org.scalaz::scalaz-zio:0.16"))
  implementation(scalaDep("org.scalaz::scalaz-zio-interop-cats:0.16"))
  implementation(scalaDep("org.scalaz::scalaz-zio-interop-monix:0.6.0"))
  implementation(scalaDep("io.monix::monix:3.0.0-RC2"))
  implementation(scalaDep("org.apache.cxf:apache-cxf:3.3.1"))

  implementation("org.springframework.boot:spring-boot-dependencies".withSpringBootVersion())
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("org.springframework.boot:spring-boot-starter-actuator")

  testImplementation(scalaDep("org.apache.kafka:kafka-streams-test-utils:2.1.0"))
}

tasks.bootJar {
  mainClassName = "com.github.damianreeves.ticketbroker.spring.server.SpringTicketBrokerServer"
}

tasks.bootRun {
  main = "com.github.damianreeves.ticketbroker.spring.server.SpringTicketBrokerServer"
  args("--spring.profiles.active=local")
}
