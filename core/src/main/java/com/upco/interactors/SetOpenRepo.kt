package com.upco.interactors

import com.upco.data.RepoRepository
import com.upco.domain.Repo

class SetOpenRepo(private val repoRepository: RepoRepository) {
    operator fun invoke(repo: Repo) = repoRepository.setOpenRepo(repo)
}