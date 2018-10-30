package com.silverhetch.nox.model.log

import com.silverhetch.clotho.Source
import java.sql.Connection

/**
 * Query logs by given tag ids.
 */
class LogsByTagName(private val dbConn: Source<Connection>, private val tagNames: Source<Array<String>>) : Source<List<NoxLog>> {
    override fun fetch(): List<NoxLog> {
        dbConn.fetch().let { connection ->
            connection.prepareStatement("""
                select log.*, tag.name
                from log
                       left join log_tags on log.id = log_tags.log_id
                       left join tag on log_tags.tag_id = tag.id
                where tag.name in (?)
                group by log.id;""").use {
                it.setString(1, tagNames.fetch()[0]) // TODO: use array instead
                it.executeQuery().use { result ->
                    return DbLogs(result).fetch()
                }
            }
        }
    }
}