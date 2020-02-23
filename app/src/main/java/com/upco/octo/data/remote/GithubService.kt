package com.upco.octo.data.remote

import com.upco.octo.data.remote.model.RepoList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    @GET("/users/{username}/repos")
    fun getReposFromUser(@Path("username") username: String): Call<RepoList>
}