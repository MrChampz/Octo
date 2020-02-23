package com.upco.octo.data

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.upco.data.SearchReposDataSource

class LocalSearchReposDataSource(ctx: Context) : SearchReposDataSource {

    private val prefs = ctx.getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
    private val editor = prefs.edit()

    private var username = prefs.getString(PREFS_USERNAME, "")!!

    override fun setUsername(username: String) {
        this.username = username
        editor.putString(PREFS_USERNAME, username)
        editor.apply()
    }

    override fun getUsername() = username

    companion object {
        private const val PREFS_NAME = "Octo_SearchRepos"
        private const val PREFS_USERNAME = "username"
    }
}