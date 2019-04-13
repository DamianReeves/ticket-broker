package com.github.damianreeves.ticketbroker.common

import scalaz.zio.{Ref, ZIO}

package object pipeline {
  def request[In,Out](initialResponse:Out)(processFn:(In, Ref[Out]) => Out)(input:In):ZIO[Pipeline, Throwable,Out] =
    ZIO.accessM(_.pipeline.request(initialResponse)(processFn)(input))
}
