package se.ohou.springeventlistenersample

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.AsyncConfigurer
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import java.util.concurrent.Executor

@EnableAsync
@SpringBootApplication
class SpringEventListenerSampleApplication  : AsyncConfigurer {

	override fun getAsyncExecutor(): Executor? {
		return ThreadPoolTaskExecutor().apply {
			corePoolSize = 2
			maxPoolSize = 100
			setQueueCapacity(5)
			initialize()
		}
	}

	override fun getAsyncUncaughtExceptionHandler(): AsyncUncaughtExceptionHandler? {
		return SimpleAsyncUncaughtExceptionHandler()
	}

}

fun main(args: Array<String>){
	runApplication<SpringEventListenerSampleApplication>(*args)
}
