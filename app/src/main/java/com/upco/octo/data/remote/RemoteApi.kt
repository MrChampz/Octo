package com.upco.octo.data.remote

import com.google.gson.GsonBuilder
import com.upco.octo.data.remote.model.RepoList
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RemoteApi {

    private const val BASE_URL = "https://api.github.com"

    private val interceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    private val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    private val gson = GsonBuilder()
        .registerTypeAdapter(RepoList::class.java, RepoListDeserializer())
        .create()

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val githubService = retrofit.create(GithubService::class.java)
}