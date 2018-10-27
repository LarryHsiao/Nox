package com.silverhetch.nox.model.tag.json

import com.silverhetch.clotho.source.ConstSource
import com.silverhetch.nox.model.tag.ConstTag
import org.junit.Assert.assertEquals
import org.junit.Test

class TagsArrayTest {
    @Test
    fun simple() {
        assertEquals(
            "[{\"name\":\"Name\"}]",
            TagsArray(
                ConstSource(listOf(ConstTag(-1, "Name")))
            ).fetch().toString()
        )
    }
}