package gloddy.groupChat.repository

import gloddy.groupChat.GroupChat
import gloddy.groupChat.GroupChatUser

interface GroupChatCommandRepository {
    fun save(groupChat: GroupChat, groupChatUser: GroupChatUser): GroupChat
}