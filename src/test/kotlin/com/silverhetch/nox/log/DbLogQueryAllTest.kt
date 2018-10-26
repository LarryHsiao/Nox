package com.silverhetch.nox.log

import com.silverhetch.clotho.database.SingleConn
import com.silverhetch.clotho.database.sqlite.InMemoryConn
import com.silverhetch.nox.DbLogInsert
import com.silverhetch.nox.DbLogQueryAll
import com.silverhetch.nox.LogType
import com.silverhetch.nox.NoxDbConn
import org.junit.Assert
import org.junit.Test

class DbLogQueryAllTest {
    @Test
    fun simple() {
        val dbConn = SingleConn(
            NoxDbConn(
                InMemoryConn()
            )
        )
        DbLogInsert(
            dbConn,
            LogType.INFO,
            "Message"
        ).fetch()

        val result = DbLogQueryAll(
            dbConn
        ).fetch()

        Assert.assertEquals(1, result.size)
        Assert.assertEquals("Message", result[0].message())
        Assert.assertEquals(LogType.INFO, result[0].type())
    }
}