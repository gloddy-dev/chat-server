package gloddy.core

enum class ErrorCode(
    val statusCode: Int,
    val errorCode: String,
    val message: String
) {
    //GroupChat
    GROUP_CHAT_NO_AUTHORIZATION(401, "GROUP_CHAT_001", "권한이 없습니다.")
}