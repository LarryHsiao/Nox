package com.silverhetch.nox

import com.silverhetch.clotho.database.SingleConn
import com.silverhetch.clotho.database.sqlite.InMemoryConn
import com.silverhetch.nox.takes.TkInsertLog
import com.silverhetch.nox.takes.TkLogs
import com.silverhetch.nox.takes.TkNoxStatus
import org.takes.facets.fork.FkMethods
import org.takes.facets.fork.FkRegex
import org.takes.facets.fork.TkFork
import org.takes.http.Exit
import org.takes.http.FtBasic

fun main(arg: Array<String>) {
    val dbConn = SingleConn(
        NoxDbConn(
            InMemoryConn()
        )
    )
    FtBasic(
        TkFork(
            FkRegex("/logs",
                TkFork(
                    FkMethods("GET", TkLogs(DbLogQueryAll(dbConn))),
                    FkMethods(listOf("POST", "PUT"), TkInsertLog(dbConn))
                )
            ),
            FkRegex("/logs/status",
                TkFork(
                    FkMethods("GET", TkNoxStatus(NoxStatusImpl(dbConn)))
                )
            )
        ),
        8080
    ).start(Exit.NEVER)
}
