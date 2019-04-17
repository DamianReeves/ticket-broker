package com.github.damianreeves.ticketbroker.common.testing

import org.scalatest.{FlatSpec, Inside, Matchers, OptionValues}
import org.scalatest.prop.PropertyChecks

trait BddSpec
  extends FlatSpec
    with PropertyChecks
    with Matchers
    with Inside
    with OptionValues
