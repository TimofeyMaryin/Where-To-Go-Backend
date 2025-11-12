package com.where.to.go.domain.models

import kotlinx.serialization.Serializable


@Serializable
data class EventModel(
    val id: String,
    val name: String,
    val about: String,
    val created: String,
    val participants: Int,
    val photo: String? = null
)
