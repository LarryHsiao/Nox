package com.silverhetch.nox.model.log

import com.silverhetch.clotho.Source
import java.sql.Connection
import java.sql.SQLException
import java.sql.Timestamp

/**
 * Database insertion of [Source]
 */
class LogInsertion(private val dbConn: Source<Connection>, private val logType: LogType, private val message: String, private val logTime:Long) : Source<NoxLog> {
    override fun fetch(): NoxLog {
        dbConn.fetch().prepareStatement("""
              insert into log (type, message, logTime)
              values (?, ?, ?);
            """).use { statement ->
            statement.setString(1, logType.name)
            statement.setString(2, message)
            statement.setTimestamp(3, Timestamp(logTime))
            statement.execute()

            statement.generatedKeys.use {
                if (it.next()) {
                    return ConstLog(
                        it.getLong(1),
                        logType,
                        message,
                        -1
                    )
                } else {
                    throw SQLException("Insert failed")
                }
            }
        }
    }
}