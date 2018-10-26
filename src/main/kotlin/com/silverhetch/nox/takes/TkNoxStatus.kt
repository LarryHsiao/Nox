package com.silverhetch.nox.takes

import com.silverhetch.nox.NoxStatus
import org.takes.Request
import org.takes.Response
import org.takes.Take
import org.takes.rs.RsJson
import javax.json.Json

/**
 * Take adapter for [NoxStatus]
 */
class TkNoxStatus(private val status: NoxStatus) : Take {
    override fun act(req: Request?): Response {
        return RsJson(
            Json.createObjectBuilder()
                .add("rows", status.rows())
                .build()
        )
    }
}