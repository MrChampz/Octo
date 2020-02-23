package com.upco.interactors

import com.upco.data.RepoRepository

class GetOpenRepo(private val repoRepository: RepoRepository) {
    operator fun invoke() = repoRepository.getOpenRepo()
}