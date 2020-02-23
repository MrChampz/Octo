package com.upco.interactors

import com.upco.data.RepoRepository

class GetRepos(private val repoRepository: RepoRepository) {
    suspend operator fun invoke(username: String) = repoRepository.getRepos(username)
}