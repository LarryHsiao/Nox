package com.silverhetch.nox.takes.tag

import com.silverhetch.clotho.Source
import com.silverhetch.clotho.log.Log
import com.silverhetch.nox.model.tag.TagInsert
import org.takes.Request
import org.takes.Response
import org.takes.Take
import org.takes.rs.RsEmpty
import java.sql.Connection
import javax.json.Json

/**
 * Take tag insertions
 */
class TkTagInsertion(private val dbConn: Source<Connection>, private val log: Log) : Take {
    override fun act(req: Request?): Response {
        if (req == null) {
            return RsEmpty()
        }
        Json.createReader(req.body()).readObject().let {
            log.info("Insert tag $it")
            TagInsert(dbConn, it.getString("name")).fetch()
        }

        return RsEmpty()
    }
}