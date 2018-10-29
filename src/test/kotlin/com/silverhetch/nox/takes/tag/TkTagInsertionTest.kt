package com.silverhetch.nox.takes.tag

import com.silverhetch.clotho.database.SingleConn
import com.silverhetch.clotho.database.sqlite.InMemoryConn
import com.silverhetch.clotho.log.PhantomLog
import com.silverhetch.nox.model.NoxDbConn
import com.silverhetch.nox.model.tag.Tags
import org.junit.Assert
import org.junit.Test
import org.takes.rq.RqFake

class TkTagInsertionTest {
    @Test
    fun simple() {
        val conn = SingleConn(
            NoxDbConn(InMemoryConn())
        )
        TkTagInsertion(
            conn,
            PhantomLog()
        ).act(RqFake(listOf(),
            """
                {
                    "name": "NewNameOfTag"
                }
                """)
        )

        Assert.assertEquals(
            "NewNameOfTag",
            Tags(conn).fetch()["NewNameOfTag"]!!.name()
        )
    }
}