package gloddy.groupChat

import gloddy.BaseServiceTest
import gloddy.groupChat.dto.command.GroupChatJoinCommand
import gloddy.groupChat.repository.GroupChatCommandRepository
import gloddy.groupChat.service.GroupChatCommander
import gloddy.groupChat.vo.MessageType
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

class GroupChatJoinTest : BaseServiceTest() {

    private lateinit var groupChatRepository: GroupChatCommandRepository
    private lateinit var groupChatCommander: GroupChatCommander

    @BeforeEach
    fun setUp() {
        groupChatRepository = mock()
        groupChatCommander = GroupChatCommander(groupChatRepository)
    }

    @Test
    @DisplayName("그룹 멤버가 채팅방 참여에 성공한다.")
    fun success_group_chat_join() {
        //given
        val command = GroupChatJoinCommand(
            groupId = 99L,
            userId = 99L
        )

        val findGroupChat = GroupChat(
            hostId = 99L,
            groupId = 99L,
            chatUserCount = 10,
            sequenceId = 999L
        )
        whenever(groupChatRepository.findByGroupId(command.groupId)).thenReturn(
            findGroupChat
        )

        //when
        groupChatCommander.join(command)

        //then
        val groupChatCaptor = argumentCaptor<GroupChat>()
        val groupChatUserCaptor = argumentCaptor<GroupChatUser>()
        val groupChatMessageCaptor = argumentCaptor<GroupChatMessage>()
        verify(groupChatRepository, times(1)).save(
            groupChatCaptor.capture(), groupChatUserCaptor.capture(), groupChatMessageCaptor.capture()
        )

        val groupChat = groupChatCaptor.firstValue
        val groupChatUser = groupChatUserCaptor.firstValue
        val groupChatMessage = groupChatMessageCaptor.firstValue

        assertEquals(groupChat.chatUserCount, findGroupChat.chatUserCount + 1)
        assertEquals(groupChatUser.userId, command.userId)
        assertEquals(groupChatUser.chatId, findGroupChat.id)
        assertEquals(groupChatMessage.userId, command.userId)
        assertEquals(groupChatMessage.chatId, findGroupChat.id)
        assertEquals(groupChatMessage.messageType, MessageType.SYSTEM_JOIN)
        assertNull(groupChatMessage.content)
    }
}