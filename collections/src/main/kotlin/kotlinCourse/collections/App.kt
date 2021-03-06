package kotlinCourse.collections

fun main() {
    showQueue()
    println()
    showStack()
}

private fun showQueue() {
    println("Queue:")
    val intQueue = Queue<Int>()
    intQueue.enqueue(3)
    intQueue.enqueue(5)
    println(intQueue.dequeue())
    intQueue.enqueue(-10)
    println(intQueue.dequeue())
    println(intQueue.dequeue())
    try {
        println(intQueue.dequeue())
    }
    catch (e: NoSuchElementException) {
        println("Calling dequeue() on empty Queue caused an exception.")
    }

    println("queueOf:")
    val strQueue = queueOf("first", "second", "third")
    while (strQueue.size > 0) {
        println(strQueue.dequeue())
    }
}

private fun showStack() {
    println("Stack:")
    val strStack = Stack<String>()
    strStack.push("first")
    strStack.push("second")
    println(strStack.pop())
    strStack.push("third")
    println(strStack.pop())
    println(strStack.pop())
    try {
        println(strStack.pop())
    }
    catch (e: NoSuchElementException) {
        println("Calling pop() on empty Stack caused an exception.")
    }

    println("stackOf:")
    val intStack = stackOf(-50, 0, 1000)
    while (intStack.size > 0) {
        println(intStack.pop())
    }
}