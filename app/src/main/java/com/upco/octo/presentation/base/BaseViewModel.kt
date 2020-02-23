package com.upco.octo.presentation.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.upco.octo.interactors.Interactors

open class BaseViewModel(application: Application, protected val interactors: Interactors) :
    AndroidViewModel(application)