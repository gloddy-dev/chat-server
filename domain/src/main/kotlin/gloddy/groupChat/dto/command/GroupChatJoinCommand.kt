package gloddy.groupChat.dto.command

data class GroupChatJoinCommand(
    val groupId: Long,
    val userId: Long
)
