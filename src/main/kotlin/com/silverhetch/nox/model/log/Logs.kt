package com.silverhetch.nox.model.log

import com.silverhetch.clotho.Source
import java.sql.Connection

/**
 * Query all row in database.
 */
class Logs(private val conn: Source<Connection>) : Source<List<NoxLog>> {
    override fun fetch(): List<NoxLog> {
        conn.fetch().createStatement().executeQuery("""select * from log order by logTime DESC;""").use {
            return DbLogs(it).fetch()
        }
    }
}