package com.upco.data

import com.upco.domain.Repo
import com.upco.domain.Response

interface RepoDataSource {

    suspend fun readAll(username: String): Response<List<Repo>>
}