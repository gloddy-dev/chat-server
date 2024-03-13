package gloddy.groupChat.dto.command

data class GroupChatDeleteMessageCommand(
    val userId: Long,
    val chatId: String,
    val chatMessageId: String
)
