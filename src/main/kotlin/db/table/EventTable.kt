package com.where.to.go.db.table

import org.jetbrains.exposed.sql.Table

object EventTable : Table("event") {
    val id = integer("id").autoIncrement()

    val name = varchar("name", 255)
    val about = varchar("about", 1024)
    val created = varchar("created", 255)
    val participants = integer("participants").default(-1)

    val photo = varchar("photo", 255).nullable()

    override val primaryKey = PrimaryKey(id)
}