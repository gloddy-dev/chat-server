package gloddy.chat.dto.event

import gloddy.chat.dto.event.eventType.ApplyEventType
import java.time.LocalDateTime

data class ApplyEvent(
        val applyId: Long,
        val eventType: ApplyEventType,
        val eventDateTime: LocalDateTime
)