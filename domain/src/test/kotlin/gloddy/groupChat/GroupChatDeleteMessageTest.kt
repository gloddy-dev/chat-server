package gloddy.groupChat

import gloddy.groupChat.dto.command.GroupChatDeleteMessageCommand
import gloddy.groupChat.event.GroupChatMessageEvent
import gloddy.groupChat.event.GroupChatMessageEventType
import gloddy.groupChat.repository.GroupChatCommandRepository
import gloddy.groupChat.service.GroupChatCommander
import gloddy.groupChat.vo.MessageType
import gloddy.util.UUIDGenerator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

class GroupChatDeleteMessageTest {

    private lateinit var groupChatRepository: GroupChatCommandRepository
    private lateinit var groupChatCommander: GroupChatCommander

    @BeforeEach
    fun setUp() {
        groupChatRepository = mock()
        groupChatCommander = GroupChatCommander(groupChatRepository)
    }

    @Test
    @DisplayName("그룹 채팅 유저가 자신의 채팅 메시지 삭제를 성공한다.")
    fun success_delete_group_chat_message() {

        //given
        val command = GroupChatDeleteMessageCommand(
            userId = 999L,
            chatId = UUIDGenerator.generate(),
            chatMessageId = UUIDGenerator.generate()
        )
        whenever(groupChatRepository.findById(command.chatId)).thenReturn(
            GroupChat(
                hostId = 999L,
                groupId = 999999L,
                chatUserCount = 10,
                id = command.chatId,
                sequenceId = 9999999L
            )
        )
        whenever(groupChatRepository.findGroupChatMessageById(command.chatMessageId)).thenReturn(
            GroupChatMessage(
                userId = command.userId,
                chatId = command.chatId,
                messageType = MessageType.USER,
                content = "채팅인데옹?",
                id = command.chatMessageId,
                sequenceId = 999999L
            )
        )

        //when
        groupChatCommander.deleteMessage(command)

        //then
        val groupChatCaptor = argumentCaptor<GroupChat>()
        val groupChatMessageCaptor = argumentCaptor<GroupChatMessage>()
        verify(groupChatRepository, times(1)).save(groupChatCaptor.capture(), groupChatMessageCaptor.capture())

        val groupChat = groupChatCaptor.firstValue
        val groupChatMessage = groupChatMessageCaptor.firstValue

        assertEquals(groupChatMessage.id, command.chatMessageId)
        assertEquals(groupChatMessage.chatId, command.chatId)
        assertEquals(groupChatMessage.messageType, MessageType.USER)
        assertTrue(groupChatMessage.deleted)
        assertNotNull(groupChatMessage.deletedAt)
        assertEquals(groupChat.id, command.chatId)
        assertTrue(
            groupChat.events.contains(
                GroupChatMessageEvent(
                    userId = command.userId,
                    eventType = GroupChatMessageEventType.DELETE,
                    groupChatMessageId = groupChatMessage.id,
                    eventDateTime = groupChatMessage.deletedAt!!
                )
            )
        )
    }

    @Test
    @DisplayName("그룹 채팅 유저가 자신의 채팅 메시지가 아닌 채팅 메시지를 삭제하여 예외가 발생한다.")
    fun fail_delete_group_chat_message_by_no_authorization() {
        //given
        val command = GroupChatDeleteMessageCommand(
            userId = 999L,
            chatId = UUIDGenerator.generate(),
            chatMessageId = UUIDGenerator.generate()
        )
        whenever(groupChatRepository.findById(command.chatId)).thenReturn(
            GroupChat(
                hostId = 999L,
                groupId = 999999L,
                chatUserCount = 10,
                id = command.chatId,
                sequenceId = 9999999L
            )
        )
        whenever(groupChatRepository.findGroupChatMessageById(command.chatMessageId)).thenReturn(
            GroupChatMessage(
                userId = command.userId + 1,
                chatId = command.chatId,
                messageType = MessageType.USER,
                content = "채팅인데옹?",
                id = command.chatMessageId,
                sequenceId = 999999L
            )
        )

        //when & then
        assertThrows(GroupChatNoAuthorizationException::class.java) {
            groupChatCommander.deleteMessage(command)
        }
    }
}