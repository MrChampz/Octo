package com.upco.octo.data

import com.upco.data.RepoDataSource
import com.upco.domain.Error
import com.upco.domain.Repo
import com.upco.domain.Response
import com.upco.octo.data.remote.RemoteApi
import com.upco.octo.data.remote.model.RepoList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response as Res
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class RemoteRepoDataSource : RepoDataSource {

    override suspend fun readAll(username: String): Response<List<Repo>> {
        return suspendCoroutine { it: Continuation<Response<List<Repo>>> ->
            RemoteApi.githubService.getReposFromUser(username).enqueue(object: Callback<RepoList> {
                override fun onResponse(call: Call<RepoList>, response: Res<RepoList>) {
                    if (response.isSuccessful) {
                        val repos = arrayListOf<Repo>()
                        response.body()?.list?.forEach { repos.add(it) }
                        it.resume(Response(repos.toList(), Error.EMPTY))
                        return
                    }

                    it.resume(Response(
                        listOf(),
                        Error(response.code(), response.message())
                    ))
                }

                override fun onFailure(call: Call<RepoList>, t: Throwable) {
                    it.resume(Response(listOf(), Error(t)))
                }
            })
        }
    }
}