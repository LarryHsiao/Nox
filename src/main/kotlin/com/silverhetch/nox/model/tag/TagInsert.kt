package com.silverhetch.nox.model.tag

import com.silverhetch.clotho.Source
import java.sql.Connection
import java.sql.SQLException

/**
 * Tag insertion with given name.
 */
class TagInsert(private val conn: Source<Connection>, private val name: String) : Source<NoxTag> {
    override fun fetch(): NoxTag {
        conn.fetch().prepareStatement("""
            insert into tag (name)
            values (?);
            """).use { statement ->
            statement.setString(1, name)
            statement.execute()
            statement.generatedKeys.use {
                if (it.next()) {
                    return ConstTag(
                        it.getLong(1),
                        name
                    )
                } else {
                    throw SQLException("insertion failed")
                }
            }
        }

    }
}