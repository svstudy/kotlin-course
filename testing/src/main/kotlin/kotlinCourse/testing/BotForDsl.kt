package kotlinCourse.testing

class BotForDsl() {
    var name: String = ""
    var rating: Int = 0

    fun talk(question: String): String = "I don't understand you. I will call a human for help."
}

fun botForDsl(block: BotForDsl.() -> Unit): BotForDsl = BotForDsl().apply(block)