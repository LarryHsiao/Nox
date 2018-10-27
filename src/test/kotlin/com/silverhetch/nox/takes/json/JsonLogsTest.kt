package com.silverhetch.nox.takes.json

import com.silverhetch.clotho.Source
import com.silverhetch.nox.ConstLog
import com.silverhetch.nox.LogType
import com.silverhetch.nox.NoxLog
import com.silverhetch.nox.takes.json.JsonLogs
import org.cactoos.text.TextOf
import org.junit.Assert
import org.junit.Test
import org.takes.rq.RqFake

class JsonLogsTest {
    @Test
    fun simple() {
        Assert.assertEquals(
            "[{\"type\":\"INFO\",\"message\":\"Message\",\"datetime\":-1}]",
                JsonLogs(
                    object : Source<List<NoxLog>> {
                        override fun fetch(): List<NoxLog> {
                            return listOf(
                                ConstLog(-1, LogType.INFO, "Message", -1)
                            )
                        }
                    }
                ).fetch().toString()
        )
    }
}