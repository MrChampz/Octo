package com.upco.domain

data class Response<T>(
    val body: T,
    val error: Error
)