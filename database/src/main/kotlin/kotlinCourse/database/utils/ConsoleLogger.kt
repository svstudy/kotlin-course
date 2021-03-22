package kotlinCourse.database.utils

class ConsoleLogger(private val level: LogLevel = LogLevel.ERROR): Logger {
    override fun Error(msg: String) {
        if (level >= LogLevel.ERROR)
            println("Error: $msg")
    }

    override fun Trace(msg: String) {
        if (level >= LogLevel.TRACE)
            println("Trace: $msg")
    }
}

enum class LogLevel {
    ERROR,
    TRACE
}