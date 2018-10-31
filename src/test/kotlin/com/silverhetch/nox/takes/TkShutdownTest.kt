package com.silverhetch.nox.takes

import org.junit.Assert
import org.junit.Test
import org.takes.rq.RqFake

class TkShutdownTest {
    @Test
    fun simple() {
        TkShutdown().let {
            it.act(RqFake())
            Assert.assertTrue(it.ready())
        }
    }
}