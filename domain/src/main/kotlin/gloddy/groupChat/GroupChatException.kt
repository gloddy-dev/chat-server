package gloddy.groupChat

import gloddy.core.ErrorCode
import gloddy.core.GloddyChatException

class GroupChatNoAuthorizationException : GloddyChatException(
    statusCode = ErrorCode.GROUP_CHAT_NO_AUTHORIZATION.statusCode,
    errorCode = ErrorCode.GROUP_CHAT_NO_AUTHORIZATION.errorCode,
    message = ErrorCode.GROUP_CHAT_NO_AUTHORIZATION.message
)