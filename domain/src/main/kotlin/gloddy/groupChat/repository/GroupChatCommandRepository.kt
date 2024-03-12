package gloddy.groupChat.repository

import gloddy.groupChat.GroupChat
import gloddy.groupChat.GroupChatMessage
import gloddy.groupChat.GroupChatUser

interface GroupChatCommandRepository {
    fun save(groupChat: GroupChat, groupChatUser: GroupChatUser, groupChatMessage: GroupChatMessage): GroupChat
}