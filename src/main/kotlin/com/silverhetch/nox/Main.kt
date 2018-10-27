package com.silverhetch.nox

import com.silverhetch.clotho.database.SingleConn
import com.silverhetch.clotho.database.sqlite.InMemoryConn
import com.silverhetch.clotho.log.BeautyLog
import com.silverhetch.nox.log.DbLogQueryAll
import com.silverhetch.nox.log.LogDbConn
import com.silverhetch.nox.takes.TkInsertLog
import com.silverhetch.nox.takes.TkNoxStatus
import com.silverhetch.nox.takes.TkPagedLogs
import org.takes.facets.fork.FkMethods
import org.takes.facets.fork.FkRegex
import org.takes.facets.fork.TkFork
import org.takes.http.Exit
import org.takes.http.FtBasic

fun main(arg: Array<String>) {
    val log = BeautyLog().fetch()
    val dbConn = SingleConn(
        LogDbConn(
            InMemoryConn()
        )
    )
    FtBasic(
        TkFork(
            FkRegex("/logs(.*)",
                TkFork(
                    FkRegex("/logs/status",
                        TkFork(
                            FkMethods("GET", TkNoxStatus(NoxStatusImpl(dbConn)))
                        )
                    ),
                    FkMethods("GET", TkPagedLogs(DbLogQueryAll(dbConn))),
                    FkMethods(listOf("POST", "PUT"), TkInsertLog(dbConn, log)))
            )
        ),
        8080
    ).start(Exit.NEVER)
}
