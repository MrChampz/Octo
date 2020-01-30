package com.upco.octo.domain

import com.upco.octo.domain.entity.Repository

interface RepositorySearchInteractor {
    fun getAll(): List<Repository>
}