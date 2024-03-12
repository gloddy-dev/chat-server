package gloddy.groupChat.repository

import gloddy.groupChat.GroupChat
import gloddy.groupChat.GroupChatMessage
import gloddy.groupChat.GroupChatUser

interface GroupChatCommandRepository {
    fun findByGroupId(groupId: Long): GroupChat
    fun save(groupChat: GroupChat, groupChatUser: GroupChatUser, groupChatMessage: GroupChatMessage): GroupChat
}