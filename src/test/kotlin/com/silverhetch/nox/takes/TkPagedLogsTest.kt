package com.silverhetch.nox.takes

import com.silverhetch.clotho.source.ConstSource
import com.silverhetch.nox.ConstLog
import com.silverhetch.nox.LogType
import org.cactoos.text.TextOf
import org.junit.Assert.assertEquals
import org.junit.Test
import org.takes.rq.RqFake

class TkPagedLogsTest {
    @Test
    fun simple() {
        assertEquals(
            "{\"count\":1,\"started\":0,\"total\":1,\"logs\":[{\"type\":\"INFO\",\"message\":\"Message\",\"datetime\":-1}]}",
            TextOf(
                TkPagedLogs(
                    ConstSource(
                        listOf(
                            ConstLog(-1, LogType.INFO, "Message", -1)
                        )
                    )
                ).act(RqFake()).body()
            ).asString()
        )
    }
}