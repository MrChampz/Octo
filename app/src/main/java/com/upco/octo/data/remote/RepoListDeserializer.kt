package com.upco.octo.data.remote

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.upco.domain.Repo
import com.upco.octo.data.remote.model.RepoList
import java.lang.reflect.Type

class RepoListDeserializer : JsonDeserializer<RepoList> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): RepoList {
        val repos = arrayListOf<Repo>()

        val jsonArray = json?.asJsonArray
        jsonArray?.forEach { repos.add(getRepo(it.asJsonObject)) }

        return RepoList(repos)
    }

    private fun getRepo(obj: JsonObject): Repo {
        val id = obj.get("id").asLong
        val name = obj.get("name").asString
        val desc = if (obj.get("description").isJsonNull) null else obj.get("description").asString
        val stargazersCount = obj.get("stargazers_count").asInt
        val forksCount = obj.get("forks_count").asInt
        val lang = if (obj.get("language").isJsonNull) null else obj.get("language").asString
        val url = obj.get("html_url").asString

        return Repo(id, name, desc, stargazersCount, forksCount, lang, url)
    }
}