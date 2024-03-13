package gloddy.sqs.listner

import gloddy.hanlder.InPayloadHandler
import gloddy.sqs.util.MessageParser
import io.awspring.cloud.sqs.annotation.SqsListener

class SqsHandler(
        private val inPayloadHandler: InPayloadHandler
) {

    @SqsListener(value = ["\${sqs.queue.apply}"])
    fun handlerApplyEvent(message: String){
        val applyPayload = MessageParser.parseApplyEvent(message)
        inPayloadHandler.handleApplyPayload(applyPayload)
    }


}