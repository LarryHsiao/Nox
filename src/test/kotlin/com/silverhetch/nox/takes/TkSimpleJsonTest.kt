package com.silverhetch.nox.takes

import com.silverhetch.clotho.source.ConstSource
import org.cactoos.text.TextOf
import org.junit.Assert.assertEquals
import org.junit.Test
import org.takes.rq.RqFake
import javax.json.Json

class TkSimpleJsonTest {
    @Test
    fun simple() {
        assertEquals(
            "{}",
            TextOf(
                TkSimpleJson(
                    ConstSource(
                        Json.createObjectBuilder().build()
                    )
                ).act(RqFake()).body()
            ).asString()
        )
    }
}