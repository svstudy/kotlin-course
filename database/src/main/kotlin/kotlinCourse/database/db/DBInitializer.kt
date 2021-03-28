package kotlinCourse.database.db

import kotlinCourse.database.utils.Logger

class DBInitializer(private val dbClient: DBClient, private val logger: Logger) {
    fun initSchema(): Boolean {
        return listOf(
            tryExecute("CREATE rent_office","""
                CREATE TABLE IF NOT EXISTS rent_office(
                    id INTEGER PRIMARY KEY, 
                    title TEXT NOT NULL, 
                    address TEXT NOT NULL
                )"""),

            tryExecute("CREATE car","""
                CREATE TABLE IF NOT EXISTS car(
                    id INTEGER PRIMARY KEY, 
                    vin TEXT NOT NULL, 
                    model TEXT NOT NULL,
                    year INTEGER NOT NULL,
                    rent_office_id INTEGER NOT NULL,
                    
                    FOREIGN KEY (rent_office_id) 
                        REFERENCES rent_office (id) 
                            ON DELETE RESTRICT
                            ON UPDATE NO ACTION
                )"""),

            tryExecute("CREATE customer","""
                CREATE TABLE IF NOT EXISTS customer(
                    id INTEGER PRIMARY KEY,
                    full_name TEXT NOT NULL,
                    phone TEXT NOT NULL, 
                    licence_nbr TEXT NOT NULL
                )"""),

            // store price as INTEGER (with multiply by 100) since SQLite doesn't have DECIMAL type
            tryExecute("CREATE car_rent","""
                CREATE TABLE IF NOT EXISTS car_rent(
                    car_id INTEGER NOT NULL,
                    customer_id INTEGER NOT NULL,
                    start_datetime TEXT NOT NULL, 
                    days INTEGER NOT NULL,
                    price INTEGER NOT NULL,     
    
                    PRIMARY KEY (car_id, customer_id),
                    FOREIGN KEY (car_id) 
                        REFERENCES car (id) 
                            ON DELETE CASCADE 
                            ON UPDATE NO ACTION,
                    FOREIGN KEY (customer_id) 
                        REFERENCES customer (id) 
                            ON DELETE CASCADE 
                            ON UPDATE NO ACTION
                )""")
        ).all { it }
    }

    fun clearSchema(): Boolean {
        return listOf(
            tryExecute("DROP rent_office","DROP TABLE IF EXISTS rent_office"),
            tryExecute("DROP car","DROP TABLE IF EXISTS car"),
            tryExecute("DROP customer","DROP TABLE IF EXISTS customer"),
            tryExecute("DROP car_rent","DROP TABLE IF EXISTS car_rent")
        ).all { it }
    }

    fun insertTestData(): Boolean {
        return listOf(
            tryExecute("INSERT test data for rent_office","""
                INSERT INTO rent_office VALUES
                (1, 'Kurskaya', 'Moscow, Zemlyanoy val 25'),
                (2, 'Universitet', 'Moscow, Pr-kt Vernadskogo 6'),
                (3, 'Savelovskaya', 'Moscow, Burirskaya 46')
                """),

            tryExecute("INSERT test data for car","""
                INSERT INTO car VALUES
                (1, '3MZBM1U73FM126396', 'Hyundai Solaris', 2018, 1),
                (2, '5NMSH13E58H181509', 'KIA Rio', 2019, 1),
                (3, '2G1FZ1EE9F9797155', 'BMW X6', 2020, 2),
                (4, 'KL1TD66698B103831', 'KIA K5', 2021, 2),
                (5, '1LNHM97V62Y676546', 'VW Passat', 2018, 3)
                """),

            tryExecute("INSERT test data for customer","""
                INSERT INTO customer VALUES
                (1, 'Tolstoy L.N.', '+7(495)111-11-11', '1910 280828'),
                (2, 'Dostoevskiy F.M.', '+7(495)222-22-22', '1881 301021'),
                (3, 'Pushkin A.S.', '+7(495)333-33-33', '1837 250599')
                """),

            tryExecute("INSERT test data for car_rent","""
                INSERT INTO car_rent VALUES
                (1, 1, '2019-02-23 12:00:00', 3, 450000),
                (5, 1, '2020-03-08 09:30:00', 2, 500000),
                (5, 2, '2018-01-07 16:25:00', 1, 249999),
                (3, 2, '2020-05-09 11:59:00', 2, 2000000),
                (4, 2, '2021-06-12 03:45:00', 10, 7000000)
                """)
        ).all { it }
    }

    private fun tryExecute(cmdName: String, sql: String): Boolean {
        return if (dbClient.execute(sql)) {
            logger.trace("'$cmdName' successful")
            true
        } else {
            logger.trace("'$cmdName' failed")
            false
        }
    }
}