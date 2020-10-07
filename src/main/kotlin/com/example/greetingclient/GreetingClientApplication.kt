package com.example.greetingclient

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.reactive.function.client.WebClient

@SpringBootApplication
class GreetingClientApplication {

    @Bean
    fun webClient(builder:WebClient.Builder) = builder
            .baseUrl("http://localhost:8080")
            .build()

}

fun main(args: Array<String>) {
    runApplication<GreetingClientApplication>(*args)
}


data class GreetingRequest(val name:String)
data class GreetingResponse(val message:String)


