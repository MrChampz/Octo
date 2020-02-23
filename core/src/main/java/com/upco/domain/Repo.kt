package com.upco.domain

data class Repo(
    val id: Long,
    val name: String,
    val description: String?,
    val stargazersCount: Int,
    val forksCount: Int,
    val language: String?,
    val url: String
) {
    companion object {
        val EMPTY = Repo(-1, "", "", 0, 0, "", "")
    }
}