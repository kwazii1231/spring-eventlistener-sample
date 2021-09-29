package se.ohou.springeventlistenersample.observe

import java.util.concurrent.CopyOnWriteArraySet

interface Subject<T> {
    fun registerObserver(observer: Observer<T>)
    fun unregisterObserver(observer: Observer<T>)
    fun notifyToAll(event : T)
}

interface Observer<T> {
    fun observe(event : T)
}

enum class WeatherCode{
    FINE,RAIN,WINDY,ETC
}
data class Weather(
    val weatherCode : WeatherCode
)

class TodaysWeatherSubject : Subject<Weather>{

    val observers = CopyOnWriteArraySet<Observer<Weather>>()

    override fun registerObserver(observer: Observer<Weather>) {
        observers.add(observer)
    }

    override fun unregisterObserver(observer: Observer<Weather>) {
        observers.remove(observer)
    }

    override fun notifyToAll(event: Weather) {
        observers.forEach {
            it.observe(event)
        }
    }
}
