package com.silverhetch.nox.model.tag

import com.silverhetch.clotho.Source
import java.sql.Connection

/**
 * Tags in database.
 */
class DbTags(private val conn: Source<Connection>) : Source<List<NoxTag>> {
    override fun fetch(): List<NoxTag> {
        conn.fetch().createStatement().use { statement ->
            statement.executeQuery("""select * from tag;""").use {
                val result = ArrayList<NoxTag>()
                while (it.next()) {
                    result.add(ConstTag(
                        it.getLong(it.findColumn("id")),
                        it.getString(it.findColumn("name"))
                    ))
                }
                return result
            }
        }
    }
}