package com.where.to.go.db.repository

import com.where.to.go.db.table.EventTable
import com.where.to.go.domain.models.EventModel
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.SqlExpressionBuilder
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

class EventRepository(private val database: Database) {

    init {
        transaction(database) {
            SchemaUtils.create(EventTable)
        }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }

    suspend fun createEvent(
        name: String,
        about: String,
        created: String,
    )  {
        dbQuery {
            EventTable.insert {
                it[EventTable.name] = name
                it[EventTable.about] = about
                it[EventTable.created] = created

            }
        }

    }

    suspend fun getAllEvents(): List<EventModel> {
        return dbQuery {
            EventTable
                .selectAll()
                .map {
                    EventModel(
                        name = it[EventTable.name],
                        about = it[EventTable.about],
                        created = it[EventTable.created],
                        id = it[EventTable.id].toString(),
                        participants = it[EventTable.participants]
                    )
                }

        }
    }

    suspend fun joinToParty(
        eventId: Int
    )  {
        dbQuery {
            EventTable.update({ EventTable.id eq eventId }) {
                with(SqlExpressionBuilder) {
                    it.update(participants, participants + 1)
                }
            }
        }

    }

}