package com.github.damianreeves.ticketbroker.common.services

import scalaz.zio.{Ref, Task, ZIO}

import scala.collection.SortedSet

object ProfileManager {
  final case class ProfileName(value:String) extends AnyVal
  object ProfileName {
    val default = ProfileName("default")
  }
  final case class Profile(name:ProfileName, priority:Int) extends Ordered[Profile] {
    override def compare(that: Profile): Int = {
      if (this.priority == that.priority) 0
      else if (this.priority > that.priority) 1
      else -1
    }
  }
  object Profile {
    val default = Profile(ProfileName.default, Int.MinValue)
  }
  final case class ActiveProfiles(profiles:SortedSet[Profile]) {
    def names: collection.Set[ProfileName] = profiles.map(_.name)
    def displayText:String = names.mkString("[",",","]")
  }
  object ActiveProfiles {
    val default = ActiveProfiles(SortedSet(Profile.default))
  }
  trait Service {
    def getActiveProfiles:Task[SortedSet[Profile]]
  }

  trait Syntax {
    def getActiveProfiles:ZIO[ProfileManager, Throwable, SortedSet[Profile]] =
      ZIO.accessM(_.profileManager.getActiveProfiles)
  }

  trait Live extends ProfileManager {
    val ref:Ref[ActiveProfiles]
    override def profileManager:Service = new Service {
      override def getActiveProfiles: Task[SortedSet[Profile]] =
        ref.get.map(_.profiles)
    }
  }

  class ProfileManagerLive(val ref:Ref[ActiveProfiles]) extends Live {

  }
}

trait ProfileManager {
  def profileManager:ProfileManager.Service
}

