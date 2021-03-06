package com.silverhetch.nox.model.log

import com.silverhetch.clotho.database.SingleConn
import com.silverhetch.clotho.database.sqlite.InMemoryConn
import org.junit.Assert
import org.junit.Test

class LogsTest {
    @Test
    fun simple() {
        val dbConn = SingleConn(
            LogDb(
                InMemoryConn()
            )
        )
        LogInsertion(
            dbConn,
            LogType.INFO,
            "Message",
            System.currentTimeMillis()
        ).fetch()

        val result = Logs(
            dbConn
        ).fetch()

        Assert.assertEquals(1, result.size)
        Assert.assertEquals("Message", result[0].message())
        Assert.assertEquals(LogType.INFO, result[0].type())
    }
}