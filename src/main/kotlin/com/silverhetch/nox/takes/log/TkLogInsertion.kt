package com.silverhetch.nox.takes.log

import com.silverhetch.clotho.Source
import com.silverhetch.clotho.log.Log
import com.silverhetch.nox.model.log.LogInsertion
import com.silverhetch.nox.model.log.LogType
import com.silverhetch.nox.model.tag.NoxTag
import com.silverhetch.nox.model.tag.TagAttachment
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
    override fun act(req: Request): Response {
        val array = Json.createReader(req.body()).readArray()
        log.info("Insert: $array")

        val existTags = Tags(dbConn).fetch().toMutableMap()
        for (jsonValue in array) {
            val json = jsonValue.asJsonObject()
            TagAttachment(
                dbConn,
                LogInsertion(
                    dbConn,
                    LogType.fromString(json.getString("type", "")),
                    json.getString("message", ""),
                    json.getJsonNumber("logTime").longValue()
                ),
                ArrayList<NoxTag>().also { tagList ->
                    json.getJsonArray("tags").forEach {
                        val newTag = (it as JsonString).string
                        if (!existTags.containsKey(newTag)) {
                            val newTag = TagInsert(dbConn, newTag).fetch()
                            tagList.add(newTag)
                            existTags[newTag.name()] = newTag
                        } else {
                            existTags[newTag]?.let { existTag -> tagList.add(existTag) }
                        }
                    }
                }
            ).fetch()
        }

        return RsEmpty()
    }
}