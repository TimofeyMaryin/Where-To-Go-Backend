package com.where.to.go.db

import com.typesafe.config.ConfigFactory
import com.where.to.go.db.repository.EventRepository
import com.where.to.go.db.table.EventTable
import com.where.to.go.db.table.UsersTable
import io.ktor.server.config.HoconApplicationConfig
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {

    suspend fun addMockupData(database: Database) {
        val events = EventRepository(database)

        if (events.getAllEvents().isEmpty()) {
            println("DatabaseFactory: create mockup data")
            for (i in 0..10) {
                events.createEvent(
                    name = "Mockup name №${i}",
                    about = "This is placeholder does not matter",
                    created = "Tuesday"
                )
            }
            return
        }

        println("DatabaseFactory: data is already exists")
    }
}