package kotlinCourse.coroutines

import kotlinx.coroutines.*

fun main() = runBlocking {
    val requiredJob = async { getTemperature() }
    val optionalJob = async { getHumidity() }

    var result = requiredJob.await()
    if (optionalJob.isCompleted) {
        result += (" " + optionalJob.await())
    }
    println(result)
}

suspend fun getTemperature(): String {
    delay(1000)
    return "20C"
}

suspend fun getHumidity(): String {
    // change this delay to something bigger to see how this result is skipped
    delay(1000)
    return "80%"
}