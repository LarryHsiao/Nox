package com.silverhetch.nox.model.logtags

import com.silverhetch.clotho.Source
import java.sql.Connection
import java.sql.SQLException

/**
 * Insertion to table log_tags
 */
class LogTagInsertion(private val dbConn: Source<Connection>, private val logId: Long, private val tagId: Long) : Source<Unit> {
    override fun fetch() {
        dbConn.fetch().prepareStatement(
            """
                insert into log_tags (log_id, tag_id)
                values (?, ?);
                """
        ).use {
            it.setLong(1, logId)
            it.setLong(2, tagId)
            it.execute()

            it.generatedKeys.use { result ->
                if (!result.next()) {
                    throw SQLException("Log tag insertion failed")
                }
            }
        }
    }
}