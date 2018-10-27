package com.silverhetch.nox.model.tag.json

import com.silverhetch.clotho.Source
import com.silverhetch.nox.model.tag.NoxTag
import javax.json.Json
import javax.json.JsonStructure

/**
 * Json array of tags
 */
class TagsArray(private val source: Source<List<NoxTag>>) : Source<JsonStructure> {
    override fun fetch(): JsonStructure {
        return Json.createArrayBuilder().apply {
            val list = source.fetch()
            list.forEach {
                add(
                    Json.createObjectBuilder()
                        .add("name", it.name())
                )
            }
        }.build()
    }
}