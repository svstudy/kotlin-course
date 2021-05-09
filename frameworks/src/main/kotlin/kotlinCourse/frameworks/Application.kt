package kotlinCourse.frameworks

import com.typesafe.config.ConfigFactory
import io.github.config4k.extract
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinCourse.frameworks.car.carComponents
import kotlinCourse.frameworks.car.carModule
import kotlinCourse.frameworks.plugins.configureSerialization
import kotlinCourse.frameworks.rentOffice.rentOfficeComponents
import kotlinCourse.frameworks.rentOffice.rentOfficeModule
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.ktor.di
import org.kodein.di.singleton

fun main() {
    val config = ConfigFactory.load().extract<AppConfig>()

    migrate(config.database)

    val engine = embeddedServer(Netty, port = config.http.port, host = config.http.host) {
        di {
            coreComponents(config)
            rentOfficeComponents()
            carComponents()
        }
        rentOfficeModule()
        carModule()
        configureSerialization()
    }
    engine.start(wait = true)
}

fun DI.Builder.coreComponents(config: AppConfig) {
    bind<AppConfig>() with singleton { config }

    bind<Database>() with singleton {
        Database.connect(
            url = config.database.url,
            user = config.database.user,
            password = config.database.password
        )
    }
}

private fun migrate(databaseConfig: DatabaseConfig) {
    Flyway
        .configure()
        .dataSource(databaseConfig.url, databaseConfig.user, databaseConfig.password)
        .load()
        .migrate()
}
