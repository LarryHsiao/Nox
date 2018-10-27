package com.silverhetch.nox.log

/**
 * Represent Nox log
 */
interface NoxLog {
    /**
     * Index of this log
     */
    fun id(): Long

    /**
     * Type of this log
     */
    fun type(): LogType

    /**
     * Message of log
     */
    fun message(): String

    /**
     * Insert time
     */
    fun insertedTime(): Long
}