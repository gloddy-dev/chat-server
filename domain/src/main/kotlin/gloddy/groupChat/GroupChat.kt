package gloddy.groupChat

import gloddy.util.UUIDGenerator
import java.time.LocalDateTime

data class GroupChat(
    val hostId: Long,
    val groupId: Long,
    val chatUserCount: Int = 1,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val id: String = UUIDGenerator.generate(),
    val sequenceId: Long = 0L,
) {

    fun participate(userId: Long): GroupChatUser {
        return GroupChatUser(
            userId = userId,
            chatId = this.id
        )
    }
}