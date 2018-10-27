package com.silverhetch.nox.takes

import com.silverhetch.clotho.Source
import com.silverhetch.nox.NoxLog
import com.silverhetch.nox.takes.json.JsonLogs
import org.takes.Request
import org.takes.Response
import org.takes.Take
import org.takes.rq.RqHref
import org.takes.rs.RsJson
import javax.json.Json

/**
 * Paged logs of [Take] implemented with index base.
 */
class TkPagedLogs(private val source: Source<List<NoxLog>>) : Take {
    companion object {
        private const val ROW_COUNT = 20
    }

    override fun act(req: Request?): Response {
        var started = 0
        val href = RqHref.Base(req).href()
        href.param("started").let {
            if (it.iterator().hasNext()) {
                started = it.first().toInt()
            }
        }

        val logs = source.fetch()
        val ended = (started + ROW_COUNT).let { ended ->
            if (ended > logs.size) {
                logs.size
            } else {
                ended
            }
        }
        started = if (started > ended) {
            ended
        } else {
            started
        }

        return RsJson(
            Json.createObjectBuilder()
                .add("count", ended - started)
                .add("started", started)
                .add("total", logs.size)
                .add("logs",
                    JsonLogs(
                        object : Source<List<NoxLog>> {
                            override fun fetch(): List<NoxLog> {
                                return logs.subList(
                                    started,
                                    ended
                                )
                            }
                        }
                    ).fetch()
                ).build()
        )
    }
}