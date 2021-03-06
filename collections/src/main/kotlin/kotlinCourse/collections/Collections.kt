package kotlinCourse.collections

fun <T> queueOf(vararg elements: T): Queue<T> {
    val result = Queue<T>()
    elements.forEach { result.enqueue(it) }
    return result
}

fun <T> stackOf(vararg elements: T): Stack<T> {
    val result = Stack<T>()
    elements.forEach { result.push(it) }
    return result
}