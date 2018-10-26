package com.silverhetch.nox.utility

import com.silverhetch.clotho.Source
import java.text.SimpleDateFormat

class SqliteDateTime(private val dateTime: String) : Source<Long> {
    override fun fetch(): Long {
        return SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateTime).time;
    }
}