package com.silverhetch.nox.model.log.json

import com.silverhetch.clotho.Source
import com.silverhetch.nox.model.log.ConstLog
import com.silverhetch.nox.model.log.LogType
import com.silverhetch.nox.model.log.NoxLog
import org.junit.Assert
import org.junit.Test

class LogsArrayTest {
    @Test
    fun simple() {
        Assert.assertEquals(
            "[{\"type\":\"INFO\",\"message\":\"Message\",\"datetime\":-1}]",
                LogsArray(
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