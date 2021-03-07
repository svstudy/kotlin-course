package kotlinCourse.collections

class EmployeeWithPassportService(val emplDao: EmployeeDAO, val passDao: PassportDAO) {
    fun getAll(): List<EmployeeWithPassport> =
        emplDao.getAll().mapNotNull { e ->
            val p = passDao.getByOwnerId(e.id)
            if (p == null) null
            else EmployeeWithPassport(e.id, e.email, e.role, p.passNbr, p.birthDate, p.firstName, p.secondName)
        }

    fun sortedByBirthDate(): List<EmployeeWithPassport> =
        getAll().sortedBy { it.birthDate }

    fun groupByRole(): Map<String, List<EmployeeWithPassport>> =
        getAll().groupBy { it.role }

    fun count(predicate: (EmployeeWithPassport) -> Boolean): Int =
        getAll().count(predicate)

}