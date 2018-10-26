package com.silverhetch.nox

/**
 * General purpose type of log
 */
enum class LogType {
    INFO,
    WARNING,
    ERROR,
    VERBOSE,
    DEBUG;

    companion object {
        /**
         * Convert log type from given string.
         */
        fun fromString(value: String): LogType {
            return try {
                valueOf(value)
            } catch (e: IllegalArgumentException) {
                INFO
            }
        }
    }
}