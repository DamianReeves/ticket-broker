package com.github.damianreeves.ticketbroker.common.model.domain.reservation

import com.github.damianreeves.ticketbroker.common.model.domain.reservation.BulkReservationActivityId.{BulkInitiateReservationActivityId, BulkUpdateReservationActivityId}

sealed abstract class BulkReservationRequest[T <: BulkReservationActivityId](activityId:T) {
  final def getActivityId:T = activityId
}

object BulkReservationRequest {

  final case class BulkInitiateReservationRequest(
    activityId:BulkInitiateReservationActivityId
  ) extends BulkReservationRequest(activityId)

  final case class BulkUpdateReservationRequest(
    activityId: BulkUpdateReservationActivityId
  ) extends BulkReservationRequest(activityId)
}




