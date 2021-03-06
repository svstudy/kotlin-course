package kotlinCourse.collections

import java.util.LinkedList

/** Represents a variable size last-in-first-out (LIFO) collection of objects. */
class Stack<T>() {
    private val dataItems: LinkedList<T> = LinkedList<T>()

    val size: Int get() = dataItems.size

    fun push(item: T) {
        dataItems.push(item)
    }

    fun pop(): T {
        return dataItems.pop()
    }
}