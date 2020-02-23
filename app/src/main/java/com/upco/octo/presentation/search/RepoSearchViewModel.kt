package com.upco.octo.presentation.search

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.upco.domain.Error
import com.upco.domain.Repo
import com.upco.domain.Response
import com.upco.octo.presentation.base.BaseViewModel
import com.upco.octo.interactors.Interactors
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RepoSearchViewModel(application: Application, interactors: Interactors) :
    BaseViewModel(application, interactors) {

    private var _username = MutableLiveData<String>().apply {
        postValue(interactors.getSearchUsername())
    }

    val username: LiveData<String> = _username

    private val response = MediatorLiveData<Response<List<Repo>>>().apply {
        addSource(_username) { username ->
            GlobalScope.launch {
                if (username != "") postValue(interactors.getRepos(username))
            }
        }
    }

    val repos: LiveData<List<Repo>> = Transformations.map(response) { response -> response.body }
    val error: LiveData<Error> = Transformations.map(response) { response -> response.error }

    val hasUsername: LiveData<Boolean> = Transformations.map(_username) { it != "" }
    val hasRepos: LiveData<Boolean> = Transformations.map(repos) { it.isNotEmpty() }
    val hasError: LiveData<Boolean> = Transformations.map(error) { it != Error.EMPTY }

    fun search(username: String) {
        _username.value = username
        _username.let { interactors.setSearchUsername(it.value!!) }
    }

    fun refresh() {
        _username.postValue(_username.value)
    }

    fun setOpenRepo(repo: Repo) {
        interactors.setOpenRepo(repo)
    }
}