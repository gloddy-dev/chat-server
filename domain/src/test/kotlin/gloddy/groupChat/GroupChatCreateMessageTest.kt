package gloddy.groupChat

import gloddy.BaseServiceTest
import gloddy.groupChat.dto.command.GroupChatCreateMessageCommand
import gloddy.groupChat.event.GroupChatMessageEvent
import gloddy.groupChat.event.GroupChatMessageEventType
import gloddy.groupChat.repository.GroupChatCommandRepository
import gloddy.groupChat.service.GroupChatCommander
import gloddy.groupChat.vo.MessageType
import gloddy.util.UUIDGenerator
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

class GroupChatCreateMessageTest : BaseServiceTest() {

    private lateinit var groupChatRepository: GroupChatCommandRepository
    private lateinit var groupChatCommander: GroupChatCommander

    @BeforeEach
    fun setUp() {
        groupChatRepository = mock()
        groupChatCommander = GroupChatCommander(groupChatRepository)
    }

    @Test
    @DisplayName("그룹 채팅 유저가 채팅 메시지 생성을 성공한다.")
    fun success_create_group_chat_message() {
        //given
        val command = GroupChatCreateMessageCommand(
            userId = 99L,
            chatId = UUIDGenerator.generate(),
            content = "채팅이지롱"
        )
        whenever(groupChatRepository.findById(command.chatId)).thenReturn(
            GroupChat(
                hostId = 999L,
                groupId = 9999L,
                chatUserCount = 20,
                id = command.chatId,
                sequenceId = 999L
            )
        )

        //when
        groupChatCommander.createMessage(command)

        //then
        val groupChatCaptor = argumentCaptor<GroupChat>()
        val groupChatMessageCaptor = argumentCaptor<GroupChatMessage>()
        verify(groupChatRepository, times(1)).save(groupChatCaptor.capture(), groupChatMessageCaptor.capture())

        val groupChat = groupChatCaptor.firstValue
        val groupChatMessage = groupChatMessageCaptor.firstValue

        assertEquals(groupChatMessage.userId, command.userId)
        assertEquals(groupChatMessage.chatId, groupChat.id)
        assertEquals(groupChatMessage.messageType, MessageType.USER)
        assertEquals(groupChatMessage.content, command.content)
        assertEquals(groupChat.id, command.chatId)
        assertTrue(groupChat.events.contains(
            GroupChatMessageEvent(
                userId = groupChatMessage.userId,
                eventType = GroupChatMessageEventType.CREATE,
                groupChatMessageId = groupChatMessage.id,
                eventDateTime = groupChatMessage.createdAt
            )
        ))
    }
}