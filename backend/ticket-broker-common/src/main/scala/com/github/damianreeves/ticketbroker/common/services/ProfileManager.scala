package com.github.damianreeves.ticketbroker.common.services

object ProfileManager {
  final case class Profile(name:String) extends AnyVal
  object Profile {
    val default = Profile("default")
  }
  final case class ActiveProfiles(value:Set[Profile])
  object ActiveProfiles {
    val default = ActiveProfiles(Set(Profile.default))
  }
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

