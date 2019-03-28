package com.github.damianreeves.ticketbroker.common.model.domain.reservation

sealed abstract class BulkReservationActivityId(value:String) {
  final def getValue:String = value
}

object BulkReservationActivityId {

  final case class BulkInitiateReservationActivityId(id:String)
    extends BulkReservationActivityId(id)

  final case class BulkUpdateReservationActivityId(id:String)
    extends BulkReservationActivityId(id)

}
