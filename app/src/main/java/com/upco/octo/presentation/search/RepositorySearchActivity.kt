package com.upco.octo.presentation.search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.upco.octo.R
import kotlinx.android.synthetic.main.appbar_repository_search.*

class RepositorySearchActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository_search)
        setSupportActionBar(toolbar)
    }
}