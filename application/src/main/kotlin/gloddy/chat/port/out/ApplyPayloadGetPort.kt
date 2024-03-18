package gloddy.chat.port.out

import gloddy.chat.dto.event.eventType.ApplyEventType
import gloddy.chat.dto.payload.ApplyPayload

interface ApplyPayloadGetPort {

    fun getApplyPayload(applyId: Long, eventType: ApplyEventType): ApplyPayload
}