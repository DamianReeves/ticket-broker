package com.github.damianreeves.ticketbroker.common.pipeline

import scalaz.zio.{Ref, Task}

trait Pipeline {
  def pipeline: Pipeline.Service
}

object Pipeline {
  trait Service {
    def request[In,Out](initialResponse:Out)(processFn:(In, Ref[Out]) => Out)(input:In):Task[Out]
  }
}

