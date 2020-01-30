package com.upco.octo

import com.upco.octo.domain.RepositorySearchInteractor
import com.upco.octo.domain.RepositorySearchInteractorImpl
import com.upco.octo.infrastructure.RepositoryRepository
import com.upco.octo.infrastructure.RepositoryRepositoryImpl
import com.upco.octo.presentation.search.RepositorySearchPresenter

object Injector {

    private fun provideRepositoryRepository(): RepositoryRepository {
        return RepositoryRepositoryImpl()
    }

    private fun provideRepositorySearchInteractor(): RepositorySearchInteractor {
        return RepositorySearchInteractorImpl(provideRepositoryRepository())
    }

    fun provideRepositorySearchPresenter(): RepositorySearchPresenter {
        return RepositorySearchPresenter(provideRepositorySearchInteractor())
    }
}