package com.where.to.go.utils

data class Response<T>(
    val isSuccess: Boolean,
    val title: String,
    val msg: T
)