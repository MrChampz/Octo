package com.upco.data

import com.upco.domain.Repo

interface OpenRepoDataSource {

    fun setOpenRepo(repo: Repo)

    fun getOpenRepo(): Repo
}