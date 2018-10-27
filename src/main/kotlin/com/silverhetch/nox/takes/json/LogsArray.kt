package com.silverhetch.nox.takes.json

import com.silverhetch.clotho.Source
import com.silverhetch.nox.log.NoxLog
import javax.json.Json
import javax.json.JsonStructure

/**
 * Source that build json array of given [NoxLog]
 */
class LogsArray(private val logs: Source<List<NoxLog>>) : Source<JsonStructure> {
    override fun fetch(): JsonStructure {
        return Json.createArrayBuilder().apply {
            logs.fetch().let { logList ->
                for (it in logList) {
                    add(Json.createObjectBuilder().apply {
                        add("type", it.type().name)
                        add("message", it.message())
                        add("datetime", it.insertedTime())
                    }.build())
                }
            }
        }.build()
    }
}