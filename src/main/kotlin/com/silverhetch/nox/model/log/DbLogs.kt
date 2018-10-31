package com.silverhetch.nox.model.log

import com.silverhetch.clotho.Source
import java.sql.ResultSet

/**
 * Builder from [ResultSet] to [NoxLog].
 */
class DbLogs(private val it: ResultSet) : Source<List<NoxLog>> {
    override fun fetch(): List<NoxLog> {
        val result = ArrayList<NoxLog>()
        while (it.next()) {
            result.add(ConstLog(
                it.getLong(it.findColumn("id")),
                LogType.fromString(it.getString(it.findColumn("type"))),
                it.getString(it.findColumn("message")),
                it.getDate(it.findColumn("logTime")).time
            ))
        }
        return result
    }
}