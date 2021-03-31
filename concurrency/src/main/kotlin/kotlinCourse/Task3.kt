package kotlinCourse

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger

fun main() {
    runPool(10)
    runPool(20)
    runPool(30)
    // calls below don't follow the task,
    // but I wanted to see negative effect of too large thread pool
    runPool(50)
    runPool(100)
    runPool(1000)
}

fun runPool(size: Int) {
    val pool = Executors.newFixedThreadPool(size)
    val value = AtomicInteger(0)
    val start = System.nanoTime()
    repeat(1_000_000) {
        pool.submit {
            value.incrementAndGet()
        }
    }
    pool.shutdown()
    pool.awaitTermination(1, TimeUnit.MINUTES)
    println("counted to $value")

    val time = System.nanoTime() - start
    println("Result for size=$size: ${time/1000_000}ms")
    println()
}