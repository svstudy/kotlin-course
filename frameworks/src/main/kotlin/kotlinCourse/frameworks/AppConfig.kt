package kotlinCourse.frameworks

data class AppConfig(
    val http: HttpConfig,
    val database: DatabaseConfig
)

data class HttpConfig(val host: String, val port: Int)

data class DatabaseConfig(val url: String, val user: String, val password: String)