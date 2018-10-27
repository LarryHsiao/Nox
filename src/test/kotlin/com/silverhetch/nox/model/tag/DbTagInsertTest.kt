package com.silverhetch.nox.model.tag

import com.silverhetch.clotho.database.sqlite.InMemoryConn
import com.silverhetch.nox.model.NoxDbConn
import org.junit.Assert
import org.junit.Test

class DbTagInsertTest {
    @Test
    fun simple() {
        DbTagInsert(
            NoxDbConn(InMemoryConn()),
            "newTag"
        ).fetch().let { result ->
            Assert.assertEquals(
                "newTag",
                result.name()
            )
        }
    }
}