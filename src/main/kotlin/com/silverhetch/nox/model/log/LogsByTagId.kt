package com.silverhetch.nox.model.log

import com.silverhetch.clotho.Source
import java.sql.Connection

/**
 * Query logs by given tag ids.
 */
class LogsByTagId(private val dbConn: Source<Connection>, private val tagIds: Source<Array<Long>>) : Source<List<NoxLog>> {
    override fun fetch(): List<NoxLog> {
        dbConn.fetch().let { connection ->
            connection.prepareStatement("""
                select log.*
                from log
                       left join log_tags on log.id = log_tags.log_id
                where log_tags.tag_id in (?)
                group by log.id;""").use {
                it.setLong(1, tagIds.fetch()[0]) // TODO: use array instead
                it.executeQuery().use { result ->
                    return DbLogs(result).fetch()
                }
            }
        }
    }
}