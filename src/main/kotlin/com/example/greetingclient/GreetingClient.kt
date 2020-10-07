package com.example.greetingclient

import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono


@Component
class GreetingClient(private val webClient:WebClient) {


    @EventListener(ApplicationReadyEvent::class)
    fun ready() {


        /** example of retrieval of a mono with error handling */
        this.webClient
                .get()
                .uri("/greeting/{name}", "Phillip")
                .retrieve()
                .bodyToMono(GreetingResponse::class.java)
                .onErrorResume { _ -> Mono.just(GreetingResponse("OH NO JOE")) }
                .subscribe {response -> println("Mono: ${response.message}") }

        /** example of a flux */
        /*this.webClient
                .get()
                .uri("/greetings/{name}", "Phillip")
                .retrieve()
                .bodyToFlux(GreetingResponse::class.java)
                .subscribe {response -> println("Flux: ${response.message}") }*/

    }


}