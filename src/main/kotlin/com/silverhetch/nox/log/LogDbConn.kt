package com.silverhetch.nox.log

import com.silverhetch.clotho.Source
import java.sql.Connection

/**
 * Log database connection.
 */
class LogDbConn(private val conn: Source<Connection>) : Source<Connection> {
    override fun fetch(): Connection {
        return conn.fetch().also { conn ->
            conn.createStatement().use { statement ->
                statement.execute("""
                    create table log (
                      id         integer primary key autoincrement,
                      type       text not null,
                      message    text not null,
                      insertTime datetime DEFAULT(STRFTIME('%Y-%m-%d %H:%M:%f', 'NOW'))
                    );""")
            }
        }
    }
}