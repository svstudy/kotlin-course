package kotlinCourse.coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.produce
import kotlin.random.Random

fun main() = runBlocking {
    val notifier = temperatureNotifier()
    while (!notifier.isClosedForReceive) {
        println(notifier.receive())
    }
}

suspend fun CoroutineScope.temperatureNotifier() = produce {
    while (true) {
        send(Random.nextInt(-30, 30))
        delay(2000)
    }
}