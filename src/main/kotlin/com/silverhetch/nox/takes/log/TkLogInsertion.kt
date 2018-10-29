package com.silverhetch.nox.takes.log

import com.silverhetch.clotho.Source
import com.silverhetch.clotho.log.Log
import com.silverhetch.nox.model.log.LogInsertion
import com.silverhetch.nox.model.log.LogType
import com.silverhetch.nox.model.logtags.LogTagInsertion
import com.silverhetch.nox.model.tag.NoxTag
import com.silverhetch.nox.model.tag.TagInsert
import com.silverhetch.nox.model.tag.Tags
import org.takes.Request
import org.takes.Response
import org.takes.Take
import org.takes.rs.RsEmpty
import java.sql.Connection
import javax.json.Json
import javax.json.JsonString

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
        val newLog = LogInsertion(
            dbConn,
            LogType.fromString(json.getString("type", "")),
            json.getString("message", ""),
            json.getJsonNumber("logTime").longValue()
        ).fetch()

        val tagList = ArrayList<NoxTag>()
        val existTags = Tags(dbConn).fetch()
        json.getJsonArray("tags").forEach {
            val newTag = (it as JsonString).string
            if (!existTags.containsKey(newTag)) {
                tagList.add(TagInsert(dbConn, newTag).fetch())
            } else {
                existTags[newTag]?.let { existTag -> tagList.add(existTag) }
            }
        }

        tagList.forEach {
            LogTagInsertion(dbConn, newLog.id(), it.id()).fetch()
        }

        return RsEmpty()
    }
}