package gloddy.util

import java.util.UUID

class UUIDGenerator {

    companion object {
        fun generate(): String = UUID.randomUUID().toString()
    }
}