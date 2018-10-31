package com.silverhetch.nox.edge

import com.jcabi.http.request.JdkRequest
import com.jcabi.http.response.RestResponse
import org.junit.Test
import org.takes.facets.fork.FkRegex
import org.takes.facets.fork.TkFork
import org.takes.http.Exit
import org.takes.http.Front
import org.takes.http.FtBasic
import java.net.HttpURLConnection
import java.net.URI


class TakesTest {
    @Test
    fun simple() {
        lateinit var front: Front
        Thread {
            front = FtBasic(
                TkFork(
                    FkRegex("/robots", "")
                ),
                21000
            )
            front.start(Exit.NEVER)
        }.start()
        Thread.sleep(1000)
        JdkRequest(URI("http://localhost:21000/robots"))
            .fetch()
            .`as`(RestResponse::class.java)
            .assertStatus(HttpURLConnection.HTTP_OK)
    }
}