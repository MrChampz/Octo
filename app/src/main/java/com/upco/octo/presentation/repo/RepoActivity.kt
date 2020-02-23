package com.upco.octo.presentation.repo

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.upco.domain.Repo
import com.upco.octo.R
import kotlinx.android.synthetic.main.appbar_repo.*
import kotlinx.android.synthetic.main.content_repo.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepoActivity : AppCompatActivity() {

    private val viewModel: RepoViewModel by viewModel()
    private lateinit var repo: Repo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo)
        setSupportActionBar(toolbar)
        loadRepo()
    }

    override fun onBackPressed() {
        if (wv_repo.canGoBack()) {
            wv_repo.goBack()
            return
        }

        super.onBackPressed()
    }

    private fun loadRepo() {
        /* Get the opened repo */
        repo = viewModel.getOpenRepo()

        /* Set the title as repo name */
        supportActionBar?.title = repo.name

        /* Setup WebView and load repo url */
        setupWebView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        wv_repo.settings.javaScriptEnabled = true

        wv_repo.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ) = false
        }

        wv_repo.webChromeClient = object : WebChromeClient() {

            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                pb_progress.progress = newProgress

                when {
                    newProgress == 100 -> {
                        pb_progress.visibility = ProgressBar.GONE
                    }
                    pb_progress.visibility == ProgressBar.GONE && newProgress < 100 -> {
                        pb_progress.visibility = ProgressBar.VISIBLE
                    }
                }
            }
        }

        wv_repo.loadUrl(repo.url)
    }
}