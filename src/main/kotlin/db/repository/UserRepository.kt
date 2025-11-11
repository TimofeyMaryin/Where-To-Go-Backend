package com.where.to.go.db.repository

import com.where.to.go.db.table.UsersTable
import com.where.to.go.domain.models.User
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class UserRepository {

    fun createUser(name: String, age: Int): Int = transaction {
        UsersTable.insert {
            it[UsersTable.name] = name
            it[UsersTable.age] = age
        } get UsersTable.id
    }

    fun getUsers(): List<User> = transaction {
        UsersTable.selectAll().map {
            User(
                id = it[UsersTable.id],
                name = it[UsersTable.name],
                age = it[UsersTable.age]
            )
        }
    }

}