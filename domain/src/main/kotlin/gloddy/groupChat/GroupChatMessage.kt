package gloddy.groupChat

import gloddy.util.UUIDGenerator
import java.time.LocalDateTime

data class GroupChatMessage(
    val userId: Long,
    val chatId: Long,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val deleted: Boolean = false,
    val deletedAt: LocalDateTime,
    val id: String = UUIDGenerator.generate(),
    val sequenceId: Long = 0L
)