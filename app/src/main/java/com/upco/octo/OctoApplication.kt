package com.upco.octo

import android.app.Application
import com.upco.data.RepoRepository
import com.upco.interactors.*
import com.upco.octo.data.InMemoryOpenRepoDataSource
import com.upco.octo.interactors.Interactors
import com.upco.octo.data.LocalSearchReposDataSource
import com.upco.octo.data.RemoteRepoDataSource
import com.upco.octo.presentation.repo.RepoViewModel
import com.upco.octo.presentation.search.RepoSearchViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class OctoApplication : Application() {

    private val dataMod = module {
        factory { RemoteRepoDataSource() }
        factory { InMemoryOpenRepoDataSource() }
        factory { LocalSearchReposDataSource(get()) }
        single { RepoRepository(
            get<RemoteRepoDataSource>(),
            get<InMemoryOpenRepoDataSource>(),
            get<LocalSearchReposDataSource>()
        ) }
    }

    private val interactorMod = module {
        single {
            Interactors(
                GetRepos(get()),
                GetOpenRepo(get()),
                SetOpenRepo(get()),
                GetSearchUsername(get()),
                SetSearchUsername(get())
            )
        }
    }

    private val presentationMod = module {
        viewModel {
            RepoSearchViewModel(get(), get())
        }
        viewModel {
            RepoViewModel(get(), get())
        }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@OctoApplication)
            modules(listOf(dataMod, interactorMod, presentationMod))
        }
    }
}