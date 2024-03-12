package gloddy.groupChat

data class GroupChatJoinResult(
    val groupChat: GroupChat,
    val groupChatUser: GroupChatUser,
    val groupChatMessage: GroupChatMessage
)