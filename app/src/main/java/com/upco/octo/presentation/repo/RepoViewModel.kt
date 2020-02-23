package com.upco.octo.presentation.repo

import android.app.Application
import com.upco.octo.interactors.Interactors
import com.upco.octo.presentation.base.BaseViewModel

class RepoViewModel(application: Application, interactors: Interactors) :
    BaseViewModel(application, interactors) {

    fun getOpenRepo() = interactors.getOpenRepo()
}