package com.github.damianreeves.ticketbroker.akka.server.web

import com.github.swagger.akka.SwaggerHttpService
import com.github.swagger.akka.model.Info

class SwaggerDocService(hostname:String, port:Int) extends SwaggerHttpService{
  override val apiClasses: Set[Class[_]] = Set(
    classOf[HomeHttpService],
    classOf[AdminHttpService]
  )

  override val host = s"$hostname:$port"
  override val apiDocsPath = "api-docs" //where you want the swagger-json endpoint exposed
  override val info = Info() //provides license and other description details
}

object SwaggerDocService {
  def apply(hostname: String, port: Int): SwaggerDocService =
    new SwaggerDocService(hostname, port)
}
