package com.where.to.go.route

import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route

class PingRoute {


    fun Route.ping() {

        route("/ping") {
            pingServer()
        }

    }



    /**
     * Here, we plan to ping the entire server.
     * This ping should be getting all inf about server like Database, Another route's etc for example.
     */
    private fun Route.pingServer() {
        get("/server") {
            call.respond(HttpStatusCode.OK, "Server Work")
        }
    }

}