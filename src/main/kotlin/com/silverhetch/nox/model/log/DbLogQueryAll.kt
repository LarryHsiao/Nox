package com.silverhetch.nox.model.log

import com.silverhetch.clotho.Source
import com.silverhetch.nox.utility.SqliteDateTime
import java.sql.Connection

/**
 * Query all row in database.
 */
class DbLogQueryAll(private val conn: Source<Connection>) : Source<List<NoxLog>> {
    override fun fetch(): List<NoxLog> {
        conn.fetch().createStatement().executeQuery("""select * from log order by insertTime DESC;""").use {
            val result = ArrayList<NoxLog>()
            while (it.next()) {
                result.add(ConstLog(
                    it.getLong(it.findColumn("id")),
                    LogType.fromString(it.getString(it.findColumn("type"))),
                    it.getString(it.findColumn("message")),
                    SqliteDateTime(it.getString(it.findColumn("insertTime"))).fetch()
                ))
            }
            return result
        }
    }
}