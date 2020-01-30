package com.upco.octo.domain

import com.upco.octo.domain.entity.Repository
import com.upco.octo.infrastructure.RepositoryRepository

class RepositorySearchInteractorImpl(private val repository: RepositoryRepository):
    RepositorySearchInteractor {

    override fun getAll(): List<Repository> = repository.getAll()
}