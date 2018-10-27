package com.silverhetch.nox.model

import com.silverhetch.clotho.Source
import com.silverhetch.nox.model.log.LogDbConn
import com.silverhetch.nox.model.logtags.LogTagsDbConn
import com.silverhetch.nox.model.tag.TagDbConn
import java.sql.Connection

/**
 * Nox database connection source.
 */
class NoxDbConn(private val source: Source<Connection>) : Source<Connection> {
    override fun fetch(): Connection {
        return TagDbConn(
            LogDbConn(
                LogTagsDbConn(source)
            )
        ).fetch()
    }
}