package gloddy.groupChat.service

import gloddy.groupChat.GroupChat
import gloddy.groupChat.dto.command.GroupChatCreateCommand
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
}