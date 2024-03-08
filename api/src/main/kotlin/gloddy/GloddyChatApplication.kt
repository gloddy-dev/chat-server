package gloddy.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GloddyChatApplication

fun main(args: Array<String>) {
    runApplication<GloddyChatApplication>(*args)
}
