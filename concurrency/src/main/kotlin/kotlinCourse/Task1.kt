package kotlinCourse

import java.lang.Thread.sleep
import kotlin.concurrent.thread

fun main() {
    val threads =  listOf(
        // custom class inherits Thread
        MyThread(),
        // explicit Runnable
        Thread(MyRunnable(), "MyRunnable"),
        // lambda
        Thread({
            sleep(1000)
            println("lambda")
        }, "lambda"),
        // dsl
        thread(name = "dsl") {
            sleep(1000)
            println("dsl")
        },
        // daemon
        thread(isDaemon = true, name = "dsl daemon") {
            sleep(10000)
            // we won't see it, because we will not wait for daemons
            println("dsl daemon")
        },
        // different priorities
        thread(priority = 1, name = "dsl priority 1") {
            sleep(1000)
            println("dsl priority 1")
        },
        thread(priority = 10, name = "dsl priority 10") {
            sleep(1000)
            println("dsl priority 10")
        }
    )

    threads.forEach {
        if (it.state == Thread.State.NEW) {
            it.start()
            println("${it.name} was started by hand")
        }
        else {
            println("${it.name} was started automatically")
        }
    }
    println()

}

class MyThread() : Thread("MyThread") {
    override fun run() {
        sleep(1000)
        println(name)
    }
}

class MyRunnable(): Runnable {
    override fun run() {
        sleep(1000)
        println("MyRunnable")
    }

}

