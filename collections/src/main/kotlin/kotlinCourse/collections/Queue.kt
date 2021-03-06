package kotlinCourse.collections

import java.util.LinkedList

/** Represents a first-in, first-out (FIFO) collection of objects. */
class Queue<T>() {
    private val dataItems: LinkedList<T> = LinkedList<T>()

    val size: Int get() = dataItems.size

    fun enqueue(item: T) {
        dataItems.add(item)
    }

    fun dequeue(): T {
        return dataItems.remove()
    }
}