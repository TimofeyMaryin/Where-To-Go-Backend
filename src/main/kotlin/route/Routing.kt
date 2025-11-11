package com.where.to.go.route

import io.ktor.resources.*
import io.ktor.server.application.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Database

fun Application.configureRouting(database: Database) {
    install(Resources)
    routing {

        PingRoute().apply { ping() }
        EventsRoute(database).apply { call() }
    }
}
