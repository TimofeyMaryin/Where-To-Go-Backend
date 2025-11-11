package com.where.to.go.db.table

import org.jetbrains.exposed.sql.Table

object UsersTable : Table("users") {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 255)
    val age = integer("age")

    override val primaryKey = PrimaryKey(id)
}
