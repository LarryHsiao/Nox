package com.silverhetch.nox.edge

import com.jcabi.http.request.JdkRequest
import com.jcabi.http.response.RestResponse
import org.junit.Test
import java.net.HttpURLConnection
import java.net.URI


class JcabiTest {
    @Test
    fun simple() {
        JdkRequest(URI("https://www.google.com/"))
            .fetch()
            .`as`(RestResponse::class.java)
            .assertStatus(HttpURLConnection.HTTP_OK)
    }
}