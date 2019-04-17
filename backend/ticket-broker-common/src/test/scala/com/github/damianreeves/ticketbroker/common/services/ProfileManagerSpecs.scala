package com.github.damianreeves.ticketbroker.common.services

import com.github.damianreeves.ticketbroker.common.services.ProfileManager.Profile
import com.github.damianreeves.ticketbroker.common.testing.BddSpec
import com.typesafe.scalalogging.LazyLogging
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ProfileManagerSpecs extends BddSpec with LazyLogging {
  behavior of "Profile"

  it should "be constructable only from a name" in {
    forAll { name:String =>
      val actualName = s"name-$name"
      val actual = Profile.create(actualName)

      actual shouldBe Profile.create(actualName, 0)
    }
  }
}
