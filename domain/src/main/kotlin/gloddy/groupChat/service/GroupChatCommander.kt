package gloddy.groupChat.service

import gloddy.groupChat.GroupChat
import gloddy.groupChat.dto.command.GroupChatCreateCommand
import gloddy.groupChat.repository.GroupChatCommandRepository
import org.springframework.stereotype.Service

@Service
class GroupChatCommander(
    private val groupChatRepository: GroupChatCommandRepository
) {

    fun createGroupChat(command: GroupChatCreateCommand) {
        val newGroupChat = GroupChat(
            hostId = command.userId,
            groupId = command.groupId
        )
        val hostGroupChatUser = newGroupChat.participate(command.userId)
        groupChatRepository.save(newGroupChat, hostGroupChatUser)
    }
}