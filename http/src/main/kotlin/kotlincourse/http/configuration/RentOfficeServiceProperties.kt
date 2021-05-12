package kotlinCourse.http.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties("app.rentofficeservice")
class RentOfficeServiceProperties(
    var url: String = ""
)