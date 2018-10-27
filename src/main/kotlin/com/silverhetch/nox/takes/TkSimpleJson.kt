package com.silverhetch.nox.takes

import com.silverhetch.clotho.Source
import org.takes.Request
import org.takes.Response
import org.takes.Take
import org.takes.rs.RsJson
import javax.json.JsonStructure

/**
 * Take response with json body only. No request parsing.
 */
class TkSimpleJson(private val source: Source<JsonStructure>) : Take {
    override fun act(req: Request?): Response {
        return RsJson(source.fetch())
    }
}