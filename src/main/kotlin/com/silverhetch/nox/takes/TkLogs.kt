package com.silverhetch.nox.takes

import com.silverhetch.clotho.Source
import com.silverhetch.nox.NoxLog
import org.takes.Request
import org.takes.Response
import org.takes.Take
import org.takes.rs.RsJson
import javax.json.Json

class TkLogs(private val logs: Source<List<NoxLog>>) : Take {
    override fun act(req: Request?): Response {
        return RsJson(
            Json.createArrayBuilder().apply {
                logs.fetch().forEach {
                    add(Json.createObjectBuilder().apply {
                        add("type", it.type().name)
                        add("message", it.message())
                        add("datetime", it.insertedTime())
                    }.build())
                }
            }.build()
        )
    }
}