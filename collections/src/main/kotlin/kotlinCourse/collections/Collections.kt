package kotlinCourse.collections

fun <T> queueOf(vararg elements: T): Queue<T> = Queue<T>(elements.asList())

fun <T> stackOf(vararg elements: T): Stack<T> = Stack<T>(elements.asList())