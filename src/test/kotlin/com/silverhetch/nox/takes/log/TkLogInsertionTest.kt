package com.silverhetch.nox.takes.log

import com.silverhetch.clotho.database.SingleConn
import com.silverhetch.clotho.database.sqlite.InMemoryConn
import com.silverhetch.clotho.log.PhantomLog
import com.silverhetch.nox.model.NoxDbConn
import com.silverhetch.nox.model.log.Logs
import org.junit.Assert
import org.junit.Test
import org.takes.rq.RqFake

class TkLogInsertionTest{
    @Test
    fun simple() {
        val conn = SingleConn(
            NoxDbConn(InMemoryConn())
        )
        TkLogInsertion(
            conn,
            PhantomLog()
        ).act(RqFake(listOf(),
            """
                {
                    "type":"abc",
                    "message": "This is message",
                    "logTime": 1234567890000
                }
                """)
        )

        Assert.assertEquals(
            "This is message",
            Logs(conn).fetch()[0].message()
        )
    }
}