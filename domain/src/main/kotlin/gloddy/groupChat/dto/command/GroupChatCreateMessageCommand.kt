package gloddy.groupChat.dto.command

data class GroupChatCreateMessageCommand(
    val userId: Long,
    val chatId: String,
    val content: String
)
