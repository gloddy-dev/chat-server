package gloddy.groupChat

import gloddy.groupChat.event.GroupChatEvent
import gloddy.groupChat.event.GroupChatMessageEvent
import gloddy.groupChat.event.GroupChatMessageEventType
import gloddy.groupChat.vo.MessageType
import gloddy.util.UUIDGenerator
import java.time.LocalDateTime
import java.time.LocalDateTime.*

data class GroupChat(
    val hostId: Long,
    val groupId: Long,
    val chatUserCount: Int = 0,
    val createdAt: LocalDateTime = now(),
    val id: String = UUIDGenerator.generate(),
    val sequenceId: Long = 0L,
    val events: MutableList<GroupChatEvent> = ArrayList(),
) {

    fun join(userId: Long): GroupChatJoinResult {
        return GroupChatJoinResult(
            groupChat = this.copy(
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

    fun createUserMessage(userId: Long, content: String): GroupChatMessage {
        val groupChatMessage = GroupChatMessage(
            userId = userId,
            chatId = this.id,
            messageType = MessageType.USER,
            content = content
        )

        this.events.add(
            GroupChatMessageEvent(
                userId = userId,
                eventType = GroupChatMessageEventType.CREATE,
                groupChatMessageId = groupChatMessage.id,
                eventDateTime = groupChatMessage.createdAt
            )
        )

        return groupChatMessage
    }
}