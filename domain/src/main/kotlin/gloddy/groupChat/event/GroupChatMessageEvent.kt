package gloddy.groupChat.event

import java.time.LocalDateTime

data class GroupChatMessageEvent(
    val userId: Long,
    val eventType: GroupChatMessageEventType,
    val groupChatMessageId: String,
    val eventDateTime: LocalDateTime
) : GroupChatEvent