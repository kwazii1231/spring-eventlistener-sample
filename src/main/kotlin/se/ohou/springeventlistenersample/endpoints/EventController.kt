package se.ohou.springeventlistenersample.endpoints

import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.web.bind.annotation.RestController

@RestController
class EventController {


    @Async
    @EventListener
    fun handleEvent(message : String) {
        println(message)
    }
}
