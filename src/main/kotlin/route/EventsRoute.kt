package com.where.to.go.route

import com.where.to.go.db.repository.EventRepository
import com.where.to.go.utils.Response
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import org.jetbrains.exposed.sql.Database

class EventsRoute(private val database: Database) {
    private val eventRepository = EventRepository(database)

    fun Route.call() {

        route("/event") {
            getAllEvents()
            joinToEvents()
        }

    }

    private fun Route.getAllEvents() {
        get("/all") {
            val events = eventRepository.getAllEvents()
            call.respond(
                HttpStatusCode.OK,
                Response(
                    isSuccess = true,
                    title = "All Events",
                    msg = events
                )
            )

        }
    }

    private fun Route.joinToEvents() {
        post("/join") {
            val id = call.receive<Map<String, Int>>()

            if (id["id"] == null) {
                call.respond(
                    HttpStatusCode.Forbidden,
                    Response(
                        isSuccess = false,
                        title = "ID is Null",
                        msg = "Cannot to parse value. ID is $id"
                    )
                )
                return@post
            }

            eventRepository.joinToParty(eventId = id["id"]!!)
            call.respond(
                HttpStatusCode.OK,
                Response(
                    isSuccess = true,
                    title = "Successfully",
                    msg = "User successfully join to party with id $id"
                )
            )
        }
    }

}

