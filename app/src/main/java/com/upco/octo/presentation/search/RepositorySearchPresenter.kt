package com.upco.octo.presentation.search

import com.upco.octo.domain.RepositorySearchInteractor

class RepositorySearchPresenter(private val interactor: RepositorySearchInteractor) {

    fun getAll() = interactor.getAll()
}