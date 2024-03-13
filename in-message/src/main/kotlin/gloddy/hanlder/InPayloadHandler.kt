package gloddy.hanlder

import gloddy.chat.port.`in`.ChatRoomEnterCreateUseCase
import gloddy.hanlder.mapper.toDomainEvent
import gloddy.payload.apply.ApplyPayload

class InPayloadHandler (
        private val chatRoomEnterCreateUseCase: ChatRoomEnterCreateUseCase
        ){
    fun handleApplyPayload(payload: ApplyPayload) {
        val applyEvent = payload.toDomainEvent()
        chatRoomEnterCreateUseCase.create(applyEvent)
    }
}