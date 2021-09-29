package se.ohou.springeventlistenersample.extras

import org.springframework.beans.factory.InitializingBean
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

@Component
class DataGenerator(private val publisher : ApplicationEventPublisher) : InitializingBean {
    val executor: ScheduledExecutorService = Executors.newSingleThreadScheduledExecutor()

    override fun afterPropertiesSet() {
        executor.schedule(this::sendMessage,1, TimeUnit.SECONDS)
    }
    
    fun sendMessage(){
        publisher.publishEvent("Hi")
        executor.schedule(this::sendMessage,2000, TimeUnit.MILLISECONDS)
    }
}
