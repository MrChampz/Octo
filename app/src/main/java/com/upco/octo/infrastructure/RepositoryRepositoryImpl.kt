package com.upco.octo.infrastructure

import com.upco.octo.domain.entity.Repository

class RepositoryRepositoryImpl: RepositoryRepository {

    private val repositories = arrayListOf(
        Repository(
            0,
            "Repositório C++",
            "Este é um repo de teste",
            50,
            10,
            "C++",
            ""
        ),
        Repository(
            1,
            "Trello",
            "Implementação do Trello em Java",
            6,
            25,
            "Java",
            ""
        ),
        Repository(
            2,
            "Repositório Java",
            "Este é um repo de teste",
            22,
            1,
            "Java",
            ""
        ),
        Repository(
            3,
            "Chrome",
            "Repositório do Chrome",
            3,
            11,
            "C++",
            ""
        ),
        Repository(
            4,
            "Chat",
            "Chat implementado em Kotlin",
            6,
            10,
            "Kotlin",
            ""
        ),
        Repository(
            4,
            "Chat",
            "Chat implementado em Kotlin",
            6,
            10,
            "Kotlin",
            ""
        ),
        Repository(
            4,
            "Chat",
            "Chat implementado em Kotlin",
            6,
            10,
            "Kotlin",
            ""
        ),
        Repository(
            4,
            "Chat",
            "Chat implementado em Kotlin",
            6,
            10,
            "Kotlin",
            ""
        ),
        Repository(
            4,
            "Chat",
            "Chat implementado em Kotlin",
            6,
            10,
            "Kotlin",
            ""
        ),
        Repository(
            4,
            "Chat",
            "Chat implementado em Kotlin",
            6,
            10,
            "Kotlin",
            ""
        ),
        Repository(
            4,
            "Chat",
            "Chat implementado em Kotlin",
            6,
            10,
            "Kotlin",
            ""
        ),
        Repository(
            4,
            "Chat",
            "Chat implementado em Kotlin",
            6,
            10,
            "Kotlin",
            ""
        ),
        Repository(
            4,
            "Chat",
            "Chat implementado em Kotlin",
            6,
            10,
            "Kotlin",
            ""
        )
    )

    override fun getAll(): List<Repository> = repositories
}