package com.silverhetch.nox.takes.log

import com.silverhetch.clotho.Source
import com.silverhetch.clotho.source.ConstSource
import com.silverhetch.nox.model.log.json.LogsArray
import com.silverhetch.nox.model.log.LogsByTagName
import org.takes.Request
import org.takes.Response
import org.takes.Take
import org.takes.rq.RqHref
import org.takes.rs.RsJson
import java.sql.Connection

/**
 * Take logs by given tagNames.
 */
class TkLogByTags(private val dbConn: Source<Connection>) : Take {

    override fun act(req: Request?): Response {
        val tagNames = RqHref.Base(req).href().param("tagNames")
        return RsJson(
            LogsArray(
                LogsByTagName(dbConn, ConstSource(tagNames.toList().toTypedArray()))
            ).fetch()
        )
    }
}