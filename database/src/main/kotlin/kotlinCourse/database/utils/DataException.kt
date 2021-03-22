package kotlinCourse.database.utils

import java.lang.Exception

class DataException(override val message: String?, val inner: Exception): Exception(message) {
}