package com.silverhetch.nox

import com.silverhetch.clotho.Source
import java.sql.Connection

/**
 * Implementation of [NoxStatus]
 */
class NoxStatusImpl(private val conn: Source<Connection>) : NoxStatus {
    override fun rows(): Long {
        return DbLogQueryAll(conn).fetch().size.toLong()
    }
}