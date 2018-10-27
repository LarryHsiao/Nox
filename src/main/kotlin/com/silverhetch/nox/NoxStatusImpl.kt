package com.silverhetch.nox

import com.silverhetch.clotho.Source
import com.silverhetch.nox.model.log.DbLogQueryAll
import java.sql.Connection

/**
 * Implementation of [NoxStatus]
 */
class NoxStatusImpl(private val conn: Source<Connection>) : NoxStatus {
    override fun rows(): Long {
        return DbLogQueryAll(conn).fetch().size.toLong()
    }
}