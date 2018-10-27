package com.silverhetch.nox

import com.silverhetch.nox.log.LogType
import org.junit.Assert.assertEquals
import org.junit.Test

class LogTypeTest {
    @Test
    fun simple() {
        assertEquals(
            LogType.INFO,
            LogType.fromString("1234")
        )
        assertEquals(
            LogType.INFO,
            LogType.fromString("INFO")
        )
        assertEquals(
            LogType.WARNING,
            LogType.fromString("WARNING")
        )
        assertEquals(
            LogType.ERROR,
            LogType.fromString("ERROR")
        )

        assertEquals(
            LogType.VERBOSE,
            LogType.fromString("VERBOSE")
        )
        assertEquals(
            LogType.DEBUG,
            LogType.fromString("DEBUG")
        )
    }
}