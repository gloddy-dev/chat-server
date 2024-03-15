package gloddy.groupChat

import gloddy.util.UUIDGenerator
import java.time.LocalDateTime

data class GroupChatUser(
    val userId: Long,
    val chatId: String,
    val deleted: Boolean = false,
    val deletedAt: LocalDateTime? = null,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val id: String = UUIDGenerator.generate(),
    val sequenceId: Long = 0L
)