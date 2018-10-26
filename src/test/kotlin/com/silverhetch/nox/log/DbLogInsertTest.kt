package com.silverhetch.nox.log

import com.silverhetch.clotho.database.sqlite.InMemoryConn
import com.silverhetch.nox.DbLogInsert
import com.silverhetch.nox.LogType
import com.silverhetch.nox.NoxDbConn
import org.junit.Assert
import org.junit.Test

class DbLogInsertTest {
    @Test
    fun simple() {
        val newLog = DbLogInsert(
            NoxDbConn(InMemoryConn()),
            LogType.INFO,
            "Message"
        ).fetch()

        Assert.assertEquals(
            "Message",
            newLog.message()
        )
    }
}