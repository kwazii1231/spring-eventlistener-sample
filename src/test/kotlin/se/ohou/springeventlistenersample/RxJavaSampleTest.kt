package se.ohou.springeventlistenersample

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit

class RxJavaSampleTest {
    @Test
    fun test() {

        val testPub2 = Observable.create<String> {
            sub ->
            sub.onNext("Jinhan")
        }.subscribeOn(Schedulers.io())

        testPub2.subscribe { println("$it - ${Thread.currentThread().name}") }

        // Operators - http://reactivex.io/documentation/operators.html
        // Creating
        val testPub1 = Observable.create<String> {
            sub ->
            sub.onNext("Hi")
        }

        val fromCallableObs = Observable.fromCallable {
            println("Called inside of Callable Block")
        }

        val justObs = Observable.just(LocalDateTime.now())

        // 평가 되는 시점 지연
        val deferedObs = Observable.defer {
            Observable.fromSupplier { LocalDateTime.now() }
        }

        val fromSupplierObs = Observable.fromSupplier { LocalDateTime.now() }

        val infineteObs = Observable
            .interval(10, TimeUnit.SECONDS)
            .map { LocalDateTime.now() }

        val rangeObs = Observable
            .range(1, 100)
            .repeat(100)

        // Transforming
        val tempTrans = Observable.range(1, 100)

        val buffered = tempTrans.buffer(20)
//        buffered.subscribe { it.forEach { item -> println(item) } }

        val flatTarget = Observable.range(1, 100)
        val flatTarget2 = Observable.range(101, 100)

//        flatTarget.flatMap {
//            outter ->
//            flatTarget2.map {
//                inner ->
//                "$outter - $inner"
//            }
//        }.subscribe { println(it) }

        val grouped = tempTrans
            .groupBy { it % 2 == 0 }
            .flatMapSingle(Observable<Int>::toList)

        grouped.subscribe { println(it.size) }

        val mapped = tempTrans.map { it*2 }

        // Filtering
        val tempRange = Observable.range(1, 100)
        val filtered = tempRange.filter { it % 2 == 0 }
        val takeOne = tempRange.take(1)
        val takeLast = tempRange.last(1)

        val skiped = tempRange.skip(10)
//        skiped.subscribe { println(it) }

        // Combining
        val temp1 = Observable.just("first","second","third")
        val temp2 = Observable.just("1","2","3")

        val startedWith = temp1.startWith(Observable.just("Start"))
        startedWith.subscribe { println(it) }

        val zipped = temp1.zipWith(temp2){
            a,b -> "$a $b"
        }

        zipped.subscribe { println(it) }


        // Error Handling
    }
}
