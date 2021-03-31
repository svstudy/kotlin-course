package kotlinCourse

import java.lang.Thread.sleep
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread

fun main() {
    val sharedState = Shared()

    val writer = thread {
        repeat(100) {
            sharedState.value++
            sharedState.atomicValue.incrementAndGet()
            sleep(50)
        }
    }

    val wacher1 = createWatcher(1, sharedState)
    val wacher2 = createWatcher(2, sharedState)
    val wacher3 = createWatcher(3, sharedState)
}

data class Shared(
    var value: Int = 0,
    var atomicValue: AtomicInteger = AtomicInteger(0)) {
}

fun createWatcher(id: Int, sharedState: Shared): Thread = thread {
    repeat(15) {
        println("$id, value: ${sharedState.value}")
        println("$id, sharedValue: ${sharedState.atomicValue}")
        sleep(500)
    }
}