package com.silverhetch.nox

import com.silverhetch.clotho.database.SingleConn
import com.silverhetch.clotho.database.sqlite.InMemoryConn
import com.silverhetch.clotho.log.BeautyLog
import com.silverhetch.nox.model.NoxDbConn
import com.silverhetch.nox.model.log.DbLogQueryAll
import com.silverhetch.nox.model.tag.DbTags
import com.silverhetch.nox.model.tag.json.TagsArray
import com.silverhetch.nox.takes.TkNoxStatus
import com.silverhetch.nox.takes.TkSimpleJson
import com.silverhetch.nox.takes.log.TkLogInsertion
import com.silverhetch.nox.takes.log.TkPagedLogs
import org.takes.facets.fork.FkMethods
import org.takes.facets.fork.FkRegex
import org.takes.facets.fork.TkFork
import org.takes.http.Exit
import org.takes.http.FtBasic

fun main(arg: Array<String>) {
    val log = BeautyLog().fetch()
    val dbConn = SingleConn(
        NoxDbConn(
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
                    FkMethods(listOf("POST", "PUT"), TkLogInsertion(dbConn, log)))
            ),
            FkRegex("/tags(.*)",
                TkFork(
                    FkMethods("GET", TkSimpleJson(TagsArray(DbTags(dbConn))))
                )
            )
        ),
        8080
    ).start(Exit.NEVER)
}
