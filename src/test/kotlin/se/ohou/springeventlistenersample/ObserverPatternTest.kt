package se.ohou.springeventlistenersample

import org.junit.jupiter.api.Test
import se.ohou.springeventlistenersample.observe.Observer
import se.ohou.springeventlistenersample.observe.TodaysWeatherSubject
import se.ohou.springeventlistenersample.observe.Weather
import se.ohou.springeventlistenersample.observe.WeatherCode

class ObserverPatternTest {

    @Test
    fun observeTest() {
        val subject = TodaysWeatherSubject()
        val observerOne = object : Observer<Weather>{
            override fun observe(event: Weather) {
                println("today's weather is ${event.weatherCode.name} : observerOne")
            }
        }
        val observerTwo = object : Observer<Weather>{
            override fun observe(event: Weather) {
                println("today's weather is ${event.weatherCode.name} : observerTwo")
            }
        }

        subject.registerObserver(observerOne)
        subject.registerObserver(observerTwo)

        subject.notifyToAll(Weather(WeatherCode.RAIN))
        subject.notifyToAll(Weather(WeatherCode.FINE))
        subject.notifyToAll(Weather(WeatherCode.ETC))
        subject.notifyToAll(Weather(WeatherCode.WINDY))

        val url = "https://bucketplace-v2-development.s3.amazonaws.com/uploads/productions/1418699310224_9P6gfFs.jpg"
        val splited = url.split("/")
        println(splited)

        println(splited.slice(4 until splited.size))
    }
}
