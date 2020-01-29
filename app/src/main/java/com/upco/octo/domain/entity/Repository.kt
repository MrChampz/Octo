package com.upco.octo.domain.entity

data class Repository(
    val id: Long,
    val name: String,
    val description: String?,
    // stargazers_count
    val stargazersCount: Int,
    // forks_count
    val forksCount: Int,
    val language: String,
    val url: String
)