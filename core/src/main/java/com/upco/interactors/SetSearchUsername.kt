package com.upco.interactors

import com.upco.data.RepoRepository

class SetSearchUsername(private val repoRepository: RepoRepository) {
    operator fun invoke(username: String) = repoRepository.setSearchUsername(username)
}