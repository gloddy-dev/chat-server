package gloddy.groupChat.service

import gloddy.groupChat.GroupChat
import gloddy.groupChat.dto.command.GroupChatCreateCommand
import gloddy.groupChat.dto.command.GroupChatCreateMessageCommand
import gloddy.groupChat.dto.command.GroupChatDeleteMessageCommand
import gloddy.groupChat.dto.command.GroupChatJoinCommand
import gloddy.groupChat.repository.GroupChatCommandRepository
import org.springframework.stereotype.Service

@Service
class GroupChatCommander(
    private val groupChatRepository: GroupChatCommandRepository,
) {

    fun createGroupChat(command: GroupChatCreateCommand) {
        val (groupChat, groupChatUser, groupChatMessage) = GroupChat(
            hostId = command.userId,
            groupId = command.groupId
        ).join(command.userId)
        groupChatRepository.save(groupChat, groupChatUser, groupChatMessage)
    }

    fun join(command: GroupChatJoinCommand) {
        val groupChat = groupChatRepository.findByGroupId(command.groupId)
        groupChat.join(command.userId)
            .let {
                groupChatRepository.save(
                    it.groupChat,
                    it.groupChatUser,
                    it.groupChatMessage
                )
            }
    }

    fun createMessage(command: GroupChatCreateMessageCommand) {
        val groupChat = groupChatRepository.findById(command.chatId)
        groupChat.createUserMessage(
            userId = command.userId,
            content = command.content
        ).let { groupChatRepository.save(groupChat, it) }
    }

    fun deleteMessage(command: GroupChatDeleteMessageCommand) {
        val groupChat = groupChatRepository.findById(command.chatId)
        val groupChatMessage = groupChatRepository.findGroupChatMessageById(command.chatMessageId)

        groupChat.deleteUserMessage(
            userId = command.userId,
            groupChatMessage = groupChatMessage
        ).let {
            groupChatRepository.save(
                groupChat = it.groupChat,
                groupChatMessage = it.groupChatMessage
            )
        }
    }
}