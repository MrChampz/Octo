package com.upco.octo.infrastructure

import com.upco.octo.domain.entity.Repository

interface RepositoryRepository {
    fun getAll(): List<Repository>
}