package com.github.damianreeves.ticketbroker.common.model.dto

import com.github.damianreeves.ticketbroker.common.model.domain.events.valuetypes.EventId

case class EventQuery(eventId:QueryOperation[EventId])
