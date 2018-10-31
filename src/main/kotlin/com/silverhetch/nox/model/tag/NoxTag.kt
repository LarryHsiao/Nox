package com.silverhetch.nox.model.tag

/**
 * Tag of log
 */
interface NoxTag {
    /**
     * Id of [NoxTag]
     */
    fun id(): Long
    /**
     * The tag`s name.
     */
    fun name(): String
}