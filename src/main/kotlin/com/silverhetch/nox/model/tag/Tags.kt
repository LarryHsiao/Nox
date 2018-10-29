package com.silverhetch.nox.model.tag

import com.silverhetch.clotho.Source
import java.sql.Connection

/**
 * Tags in database.
 */
class Tags(private val conn: Source<Connection>) : Source<Map<String, NoxTag>> {
    override fun fetch(): Map<String, NoxTag> {
        conn.fetch().createStatement().use { statement ->
            statement.executeQuery("""select * from tag;""").use {
                val result = HashMap<String, NoxTag>()
                while (it.next()) {
                    val name = it.getString(it.findColumn("name"))
                    result[name] = ConstTag(
                        it.getLong(it.findColumn("id")),
                        name
                    )
                }
                return result
            }
        }
    }
}