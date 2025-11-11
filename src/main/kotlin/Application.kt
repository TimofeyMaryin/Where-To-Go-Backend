package com.where.to.go

import com.where.to.go.db.DatabaseFactory
import com.where.to.go.di.configureFrameworks
import com.where.to.go.route.configureRouting
import com.where.to.go.utils.configureAdministration
import com.where.to.go.utils.configureSecurity
import com.where.to.go.utils.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.jetbrains.exposed.sql.Database

fun main(args: Array<String>) {
    embeddedServer(
        Netty,
        port = 8080,
        host = "0.0.0.0",
        module = Application::module,
    ).start(wait = true)
}

suspend fun Application.module() {
    val database = Database.connect(
        "jdbc:h2:file:./test;DB_CLOSE_DELAY=-1;",
        driver = "org.h2.Driver")


    DatabaseFactory.addMockupData(database)

    configureAdministration()
    configureFrameworks()
    configureSerialization()
    configureSecurity()
    configureRouting(database)
}
