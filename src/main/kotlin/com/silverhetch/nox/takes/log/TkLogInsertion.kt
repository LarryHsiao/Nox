package com.silverhetch.nox.takes.log

import com.silverhetch.clotho.Source
import com.silverhetch.clotho.log.Log
import com.silverhetch.nox.model.log.DbLogInsert
import com.silverhetch.nox.model.log.LogType
import com.silverhetch.nox.model.tag.DbTagInsert
import org.takes.Request
import org.takes.Response
import org.takes.Take
import org.takes.rs.RsEmpty
import java.sql.Connection
import javax.json.Json

/**
 * Log insertion of [Take]
 */
class TkLogInsertion(private val dbConn: Source<Connection>, private val log: Log) : Take {
    override fun act(req: Request?): Response {
        if (req == null) {
            return RsEmpty()
        }
        val json = Json.createReader(req.body()).readObject()
        log.info("Insert: $json")
        DbLogInsert(
            dbConn,
            LogType.fromString(json.getString("type", "")),
            json.getString("message", ""),
            json.getJsonNumber("logTime").longValue()
        ).fetch()
        return RsEmpty()
    }
}