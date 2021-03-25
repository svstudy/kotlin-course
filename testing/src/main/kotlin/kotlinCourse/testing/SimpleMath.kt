package kotlinCourse.testing

class SimpleMath {
    fun isEven(x: Int): Boolean {
        return x % 2 == 0
    }

    fun sig(x: Int): Int {
        if (x < 0) return -1
        if (x == 0) return 0
        return 1
    }
}