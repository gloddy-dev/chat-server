package gloddy.chat.dto.payload

data class ApplyPayload (
    val groupId: Long,
    val captainId: Long,
    val applyUserId: Long,
    val groupImage: String
)