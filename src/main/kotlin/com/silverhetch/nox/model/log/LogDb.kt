package com.silverhetch.nox.model.log

import com.silverhetch.clotho.Source
import java.sql.Connection

/**
 * Log database connection.
 */
class LogDb(private val conn: Source<Connection>) : Source<Connection> {
    override fun fetch(): Connection {
        return conn.fetch().also { conn ->
            conn.createStatement().use { statement ->
                statement.execute("""
                    create table if not exists log (
                      id         integer primary key autoincrement,
                      type       text not null,
                      message    text not null,
                      logTime datetime DEFAULT(STRFTIME('%Y-%m-%d %H:%M:%f', 'NOW'))
                    );""")
            }
        }
    }
}