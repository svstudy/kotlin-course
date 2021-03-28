package kotlinCourse.database.utils

class ConsoleLogger(private val level: LogLevel = LogLevel.ERROR): Logger {
    override fun error(msg: String) {
        if (level >= LogLevel.ERROR)
            println("Error: $msg")
    }

    override fun trace(msg: String) {
        if (level >= LogLevel.TRACE)
            println("Trace: $msg")
    }
}

enum class LogLevel {
    ERROR,
    TRACE
}