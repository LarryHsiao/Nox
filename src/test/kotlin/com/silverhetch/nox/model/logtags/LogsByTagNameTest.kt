package com.silverhetch.nox.model.logtags

import com.silverhetch.clotho.database.SingleConn
import com.silverhetch.clotho.database.sqlite.InMemoryConn
import com.silverhetch.clotho.source.ConstSource
import com.silverhetch.nox.model.NoxDbConn
import com.silverhetch.nox.model.log.LogInsertion
import com.silverhetch.nox.model.log.LogType
import com.silverhetch.nox.model.log.LogsByTagName
import org.junit.Test

/**
 * Logs testing
 */
class LogsByTagNameTest {
    @Test
    fun simple() {
        val dbConn = SingleConn(NoxDbConn(InMemoryConn()))
        LogInsertion(dbConn, LogType.INFO, "This is message", -1).fetch()
        LogsByTagName(dbConn, ConstSource(arrayOf("MyNewTestTag"))).fetch()
    }
}