package gloddy.core

abstract class GloddyChatException(
    val statusCode: Int,
    val errorCode: String,
    override val message: String
): RuntimeException(message)