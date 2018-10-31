package com.silverhetch.nox.takes

import org.takes.Request
import org.takes.Response
import org.takes.Take
import org.takes.http.Exit
import org.takes.rs.RsEmpty

class TkShutdown :Take , Exit{
    private var ready = false
    override fun act(req: Request?): Response {
        ready = true
        return RsEmpty()
    }

    override fun ready(): Boolean {
        return ready
    }
}