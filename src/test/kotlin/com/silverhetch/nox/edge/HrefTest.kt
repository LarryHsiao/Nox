package com.silverhetch.nox.edge

import org.junit.Assert
import org.junit.Test
import org.takes.misc.Href

class HrefTest {
    @Test
    fun simple() {
        Assert.assertTrue(Href("https://localhost?field=123").param("field").iterator().hasNext())
        Assert.assertFalse(Href("https://localhost").param("field").iterator().hasNext())
        Assert.assertEquals("123",Href("https://localhost?field=123").param("field").first())
    }
}