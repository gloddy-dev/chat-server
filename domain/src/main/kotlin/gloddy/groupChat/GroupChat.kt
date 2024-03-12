package gloddy.groupChat

import gloddy.groupChat.vo.MessageType
import gloddy.util.UUIDGenerator
import java.time.LocalDateTime

data class GroupChat(
    val hostId: Long,
    val groupId: Long,
    val chatUserCount: Int = 0,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val id: String = UUIDGenerator.generate(),
    val sequenceId: Long = 0L,
) {

    fun join(userId: Long): GroupChatJoinResult {
        return GroupChatJoinResult(
            groupChat =  this.copy(
                chatUserCount = this.chatUserCount + 1
            ),
            groupChatUser = GroupChatUser(
                userId = userId,
                chatId = this.id
            ),
            groupChatMessage = GroupChatMessage(
                userId = userId,
                chatId = this.id,
                messageType = MessageType.SYSTEM_JOIN,
                content = null
            )
        )
    }
}