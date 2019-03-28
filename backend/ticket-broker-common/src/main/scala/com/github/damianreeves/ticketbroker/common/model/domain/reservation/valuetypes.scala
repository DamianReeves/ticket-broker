package com.github.damianreeves.ticketbroker.common.model.domain.reservation

import com.github.damianreeves.ticketbroker.common.model.domain.events.valuetypes.EventSummary

object valuetypes {

  final case class ReservationInfo(events:List[EventSummary])
}
