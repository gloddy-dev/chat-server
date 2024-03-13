package gloddy.groupChat.repository

import gloddy.groupChat.GroupChat
import gloddy.groupChat.GroupChatMessage
import gloddy.groupChat.GroupChatUser

interface GroupChatCommandRepository {
    fun findById(chatId: String): GroupChat
    fun findByGroupId(groupId: Long): GroupChat
    fun save(groupChat: GroupChat, groupChatUser: GroupChatUser, groupChatMessage: GroupChatMessage): GroupChat
    fun save(groupChat: GroupChat, groupChatMessage: GroupChatMessage): GroupChatMessage
}