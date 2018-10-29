package com.silverhetch.nox.model.tag.json

import com.silverhetch.clotho.Source
import com.silverhetch.nox.model.tag.NoxTag
import javax.json.Json
import javax.json.JsonStructure

/**
 * Json array of tags
 */
class TagsArray(private val source: Source<Map<String,NoxTag>>) : Source<JsonStructure> {
    override fun fetch(): JsonStructure {
        return Json.createArrayBuilder().apply {
            val map = source.fetch()
            map.entries.forEach {
                add(
                    Json.createObjectBuilder()
                        .add("name", it.value.name())
                )
            }
        }.build()
    }
}