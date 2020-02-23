package com.upco.data

import com.upco.domain.Repo

class RepoRepository(
    private val repoDataSource: RepoDataSource,
    private val openRepoDataSource: OpenRepoDataSource,
    private val searchReposDataSource: SearchReposDataSource
) {
    suspend fun getRepos(username: String) = repoDataSource.readAll(username)

    fun setOpenRepo(repo: Repo) = openRepoDataSource.setOpenRepo(repo)

    fun getOpenRepo() = openRepoDataSource.getOpenRepo()

    fun setSearchUsername(username: String) = searchReposDataSource.setUsername(username)

    fun getSearchUsername() = searchReposDataSource.getUsername()
}