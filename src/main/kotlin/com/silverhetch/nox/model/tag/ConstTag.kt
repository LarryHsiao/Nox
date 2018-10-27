package com.silverhetch.nox.model.tag

/**
 * Const of [NoxTag].
 */
class ConstTag(private val id: Long, private val name: String) : NoxTag {
    override fun id(): Long {
        return id
    }

    override fun name(): String {
        return name
    }
}