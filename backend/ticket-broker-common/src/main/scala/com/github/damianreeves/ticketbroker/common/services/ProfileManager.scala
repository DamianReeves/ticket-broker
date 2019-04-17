package com.github.damianreeves.ticketbroker.common.services

object ProfileManager {
  final case class Profile(name:String) extends AnyVal
  final case class ActiveProfiles(value:Set[Profile])
  trait Service {

  }

  trait Syntax {

  }

  object Live extends ProfileManager {
    override def profileManager:Service = new Service {

    }
  }
}

trait ProfileManager {
  def profileManager:ProfileManager.Service
}

