package gloddy.groupChat

import gloddy.groupChat.vo.MessageType
import gloddy.util.UUIDGenerator
import java.time.LocalDateTime

data class GroupChatMessage(
    val userId: Long,
    val chatId: String,
    val messageType: MessageType,
    val content: String?,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val deleted: Boolean = false,
    val deletedAt: LocalDateTime? = null,
    val id: String = UUIDGenerator.generate(),
    val sequenceId: Long = 0L
)