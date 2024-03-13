package gloddy.chat.port.`in`

import gloddy.chat.dto.event.ApplyEvent

interface ChatRoomEnterCreateUseCase {

    fun create(event : ApplyEvent)
}