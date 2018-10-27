package com.silverhetch.nox.takes.log

import com.silverhetch.clotho.Source
import com.silverhetch.clotho.log.Log
import com.silverhetch.nox.model.log.DbLogInsert
import com.silverhetch.nox.model.log.LogType
import org.takes.Request
import org.takes.Response
import org.takes.Take
import org.takes.rs.RsEmpty
import java.sql.Connection
import javax.json.Json

class TkLogInsertion(private val dbConn: Source<Connection>, private val log: Log) : Take {
    override fun act(req: Request?): Response {
        if (req == null) {
            return RsEmpty()
        }
        Json.createReader(req.body()).readObject().let {
            log.info("Insert: $it")
            DbLogInsert(
                dbConn,
                LogType.fromString(it.getString("type", "")),
                it.getString("message", "")
            ).fetch()
        }
        return RsEmpty()
    }
}