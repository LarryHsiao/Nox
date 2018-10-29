package com.silverhetch.nox.model.tag

import com.silverhetch.clotho.Source
import java.sql.Connection

/**
 * Tag database connection.
 */
class TagDb(private val dbConn: Source<Connection>) : Source<Connection> {
    override fun fetch(): Connection {
        return dbConn.fetch().apply {
            createStatement().use { statement ->
                statement.execute("""
                    create table if not exists tag (
                      id      integer primary key autoincrement,
                      name text not null unique
                    );""")
            }
        }
    }
}