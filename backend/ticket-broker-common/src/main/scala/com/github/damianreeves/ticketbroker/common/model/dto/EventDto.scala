package com.github.damianreeves.ticketbroker.common.model.dto


import java.time.{Instant, ZonedDateTime}

import com.github.damianreeves.ticketbroker.common.model.domain.events.valuetypes.EventTitle

case class EventDto(title:EventTitle, startTime:Instant, endTime:ZonedDateTime)
