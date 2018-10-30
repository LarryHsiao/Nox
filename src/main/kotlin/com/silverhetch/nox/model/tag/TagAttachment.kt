package com.silverhetch.nox.model.tag

import com.silverhetch.clotho.Source
import com.silverhetch.nox.model.log.NoxLog
import com.silverhetch.nox.model.logtags.LogTagInsertion
import java.sql.Connection

/**
 * Source that attach given tags to log
 */
class TagAttachment(private val dbConn: Source<Connection>, private val logSource: Source<NoxLog>, private val tagList: List<NoxTag>) : Source<NoxLog> {
    override fun fetch(): NoxLog {
        return logSource.fetch().also { log ->
            for (noxTag in tagList) {
                LogTagInsertion(dbConn, log.id(), noxTag.id()).fetch()
            }
        }
    }
}