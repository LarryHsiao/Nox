package com.silverhetch.nox

import com.silverhetch.clotho.Source
import com.silverhetch.nox.model.log.Logs
import java.sql.Connection

/**
 * Implementation of [NoxStatus]
 */
class NoxStatusImpl(private val conn: Source<Connection>) : NoxStatus {
    override fun rows(): Long {
        return Logs(conn).fetch().size.toLong()
    }
}