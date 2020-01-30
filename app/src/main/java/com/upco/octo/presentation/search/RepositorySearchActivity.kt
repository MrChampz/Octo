package com.upco.octo.presentation.search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.upco.octo.Injector
import com.upco.octo.R
import kotlinx.android.synthetic.main.appbar_repository_search.*
import kotlinx.android.synthetic.main.content_repository_search.*

class RepositorySearchActivity: AppCompatActivity() {

    private val presenter = Injector.provideRepositorySearchPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository_search)
        setSupportActionBar(toolbar)

        setupRepositories()
    }

    private fun setupRepositories() {
        rv_repositories.adapter = RepositorySearchAdapter(presenter.getAll())

        val layoutManager = LinearLayoutManager(this)
        rv_repositories.layoutManager = layoutManager

        val dividerDeco = DividerItemDecoration(
            rv_repositories.context,
            layoutManager.orientation
        )
        rv_repositories.addItemDecoration(dividerDeco)
    }
}