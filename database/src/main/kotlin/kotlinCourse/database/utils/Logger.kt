package kotlinCourse.database.utils

interface Logger {
    fun error(msg: String)
    fun trace(msg: String)
}