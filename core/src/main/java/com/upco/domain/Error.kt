package com.upco.domain

data class Error(
    val code: Int?,
    val message: String?,
    val throwable: Throwable?
) {
    constructor(code: Int, message: String): this(code, message, null)
    constructor(throwable: Throwable): this(null, null, throwable)

    companion object {
        val EMPTY = Error(0, "", null)
    }
}