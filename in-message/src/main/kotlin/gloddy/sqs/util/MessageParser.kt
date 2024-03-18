package gloddy.sqs.util

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import gloddy.payload.apply.ApplyPayload

class MessageParser {

    companion object{
        private val objectMapper = jacksonObjectMapper().registerModule(JavaTimeModule())

        fun parseApplyEvent(message: String): ApplyPayload {
            val payload = parsePayloadFromMessage(message)
            return objectMapper.readValue(payload , ApplyPayload::class.java)
        }


        private fun parsePayloadFromMessage(message: String): String {
            val outerMessage: Map<String, Any> = objectMapper.readValue(message, Map::class.java) as Map<String, Any>
            return outerMessage["Message"] as String
        }
    }
}