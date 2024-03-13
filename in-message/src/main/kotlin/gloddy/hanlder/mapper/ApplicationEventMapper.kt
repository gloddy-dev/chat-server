package gloddy.hanlder.mapper

import gloddy.chat.dto.event.eventType.ApplyEventType
import gloddy.chat.dto.event.ApplyEvent
import gloddy.payload.apply.ApplyPayload
import gloddy.payload.apply.ApplyPayloadType


fun ApplyPayload.toDomainEvent(): ApplyEvent =
        ApplyEvent(
                applyId = this.applyId,
                eventType = this.eventType.toDomainEventType(),
                eventDateTime = this.eventDateTime
        )

private fun ApplyPayloadType.toDomainEventType(): ApplyEventType =
        when (this) {
            ApplyPayloadType.APPLY_APPROVE -> ApplyEventType.APPLY_APPROVE
        }