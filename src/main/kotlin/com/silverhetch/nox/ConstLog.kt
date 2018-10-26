package com.silverhetch.nox

/**
 * Database of [NoxLog]
 */
class ConstLog(private val id: Long,
               private val type: LogType,
               private val message: String,
               private val insertedTime: Long) : NoxLog {
    override fun id(): Long {
        return id
    }

    override fun type(): LogType {
        return type
    }

    override fun message(): String {
        return message
    }

    override fun insertedTime(): Long {
        return insertedTime
    }
}