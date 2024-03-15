package gloddy.groupChat

import gloddy.BaseServiceTest
import gloddy.groupChat.dto.command.GroupChatCreateCommand
import gloddy.groupChat.repository.GroupChatCommandRepository
import gloddy.groupChat.service.GroupChatCommander
import gloddy.groupChat.vo.MessageType
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.kotlin.verify
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.times
import org.mockito.kotlin.mock


class GroupChatCreateTest : BaseServiceTest() {

    private lateinit var groupChatRepository: GroupChatCommandRepository
    private lateinit var groupChatCommander: GroupChatCommander

    @BeforeEach
    fun setUp() {
        groupChatRepository = mock()
        groupChatCommander = GroupChatCommander(groupChatRepository)
    }

    @Test
    @DisplayName("그룹 호스트가 채팅방 생성을 성공한다.")
    fun success_group_chat_create() {

        //given
        val command = GroupChatCreateCommand(
            userId = 99L,
            groupId = 99L
        )

        //when
        groupChatCommander.createGroupChat(command)

        //then
        val groupChatCaptor = argumentCaptor<GroupChat>()
        val groupChatUserCaptor = argumentCaptor<GroupChatUser>()
        val groupChatMessageCaptor = argumentCaptor<GroupChatMessage>()
        verify(groupChatRepository, times(1)).save(groupChatCaptor.capture(), groupChatUserCaptor.capture(), groupChatMessageCaptor.capture())

        val groupChat = groupChatCaptor.firstValue
        val groupChatUser = groupChatUserCaptor.firstValue
        val groupChatMessage = groupChatMessageCaptor.firstValue

        assertEquals(groupChat.groupId, command.groupId)
        assertEquals(groupChat.hostId, command.userId)
        assertEquals(groupChatUser.userId, command.userId)
        assertEquals(groupChatUser.chatId, groupChat.id)
        assertEquals(groupChatMessage.userId, command.userId)
        assertEquals(groupChatMessage.chatId, groupChat.id)
        assertEquals(groupChatMessage.messageType, MessageType.SYSTEM_JOIN)
    }
}