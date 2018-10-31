package com.silverhetch.nox.model

import com.silverhetch.clotho.Source
import com.silverhetch.nox.model.log.LogDb
import com.silverhetch.nox.model.logtags.LogTagsDb
import com.silverhetch.nox.model.tag.TagDb
import java.sql.Connection

/**
 * Nox database connection source.
 */
class NoxDbConn(private val source: Source<Connection>) : Source<Connection> {
    override fun fetch(): Connection {
        return TagDb(
            LogDb(
                LogTagsDb(source)
            )
        ).fetch()
    }
}