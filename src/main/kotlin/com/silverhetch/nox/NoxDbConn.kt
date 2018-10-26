package com.silverhetch.nox

import com.silverhetch.clotho.Source
import java.sql.Connection

/**
 * Log database connection.
 */
class NoxDbConn(private val conn: Source<Connection>) : Source<Connection> {
    override fun fetch(): Connection {
        return conn.fetch().also { conn ->
            conn.createStatement().use { statement ->
                statement.execute("""
                    create table log (
                      id         integer primary key autoincrement,
                      type       text not null,
                      message    text not null,
                      insertTime date
                    );""")

                statement.execute("""
                    create trigger insert_log_with_time
                      after insert
                      on log
                    begin
                      update log
                      set insertTime = DATETIME('now')
                      where ROWID = new.ROWID;
                    end;
                """)
            }
        }
    }
}