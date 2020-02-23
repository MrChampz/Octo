package com.upco.interactors

import com.upco.data.RepoRepository

class GetSearchUsername(private val repoRepository: RepoRepository) {
    operator fun invoke() = repoRepository.getSearchUsername()
}