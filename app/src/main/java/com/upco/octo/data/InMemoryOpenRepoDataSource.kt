package com.upco.octo.data

import com.upco.data.OpenRepoDataSource
import com.upco.domain.Repo

class InMemoryOpenRepoDataSource : OpenRepoDataSource {

    private var openRepo: Repo = Repo.EMPTY

    override fun setOpenRepo(repo: Repo) {
        openRepo = repo
    }

    override fun getOpenRepo() = openRepo
}