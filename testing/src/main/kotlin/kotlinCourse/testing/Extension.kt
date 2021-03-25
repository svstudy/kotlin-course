package kotlinCourse.testing

import kotlin.jvm.Throws

@Throws(ArithmeticException::class)
fun Int.divideBy(denominator: Int): Int {
    return this / denominator
}