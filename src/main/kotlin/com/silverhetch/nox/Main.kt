package com.silverhetch.nox

import com.silverhetch.clotho.database.SingleConn
import com.silverhetch.clotho.database.sqlite.InMemoryConn
import com.silverhetch.clotho.database.sqlite.SQLiteConn
import com.silverhetch.clotho.log.BeautyLog
import com.silverhetch.nox.model.NoxDbConn
import com.silverhetch.nox.model.log.DbLogQueryAll
import com.silverhetch.nox.model.tag.DbTags
import com.silverhetch.nox.model.tag.json.TagsArray
import com.silverhetch.nox.takes.TkNoxStatus
import com.silverhetch.nox.takes.TkShutdown
import com.silverhetch.nox.takes.TkSimpleJson
import com.silverhetch.nox.takes.log.TkLogInsertion
import com.silverhetch.nox.takes.log.TkPagedLogs
import org.apache.commons.cli.DefaultParser
import org.apache.commons.cli.Option
import org.apache.commons.cli.Options
import org.takes.facets.fork.FkMethods
import org.takes.facets.fork.FkRegex
import org.takes.facets.fork.TkFork
import org.takes.http.FtBasic

fun main(arg: Array<String>) {
    val cmd = DefaultParser().parse(Options().apply {
        addOption(Option("p", "port", true, "Running Port"))
        addOption(Option("d", "database", true, "Database path"))
    }, arg)

    val port = cmd.getOptionValue("port", "8080").toInt()
    val databasePath = cmd.getOptionValue("database", ":memory:")

    val log = BeautyLog().fetch()
    val dbConn = SingleConn(
        NoxDbConn(
            SQLiteConn(databasePath)
        )
    )
    val tkShutdown = TkShutdown()
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
            ),
            FkRegex("/Exit", tkShutdown)
        ),
        port
    ).start(tkShutdown)
}
