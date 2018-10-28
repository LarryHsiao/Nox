package com.silverhetch.nox.model.log

import com.silverhetch.clotho.database.sqlite.InMemoryConn
import org.junit.Assert
import org.junit.Test

class DbLogInsertTest {
    @Test
    fun simple() {
        val newLog = DbLogInsert(
            LogDbConn(InMemoryConn()),
            LogType.INFO,
            "Message",
            System.currentTimeMillis()
        ).fetch()

        Assert.assertEquals(
            "Message",
            newLog.message()
        )
    }
}