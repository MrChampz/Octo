package com.upco.octo.presentation.search

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.upco.domain.Error
import com.upco.domain.Repo
import com.upco.octo.R
import com.upco.octo.presentation.repo.RepoActivity
import kotlinx.android.synthetic.main.appbar_repo_search.*
import kotlinx.android.synthetic.main.content_repo_search.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.net.UnknownHostException

class RepoSearchActivity: AppCompatActivity(), OnRepoClickListener {

    private val viewModel: RepoSearchViewModel by viewModel()
    private lateinit var adapter: RepoSearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_search)
        setSupportActionBar(toolbar)

        setupViewModel()
        setupSwipeRefreshLayout()
        setupRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the SearchView menu
        menuInflater.inflate(R.menu.menu_repo_search, menu)

        // Get the SearchView menu item
        val searchItem = menu.findItem(R.id.mn_search)

        // Then get SearchView action view from menu item
        val searchView = searchItem?.actionView as SearchView

        // And setup it
        setupSearchView(searchView)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onRepoClick(repo: Repo) {
        viewModel.setOpenRepo(repo)
        startActivity(Intent(this, RepoActivity::class.java))
    }

    private fun setupViewModel() {
        viewModel.username.observe(this, Observer { username ->
            if (username.isNotBlank()) toolbar.title = username
        })

        viewModel.repos.observe(this, Observer { repos ->
            if (repos.isNotEmpty()) {
                hideStateInfo()
                adapter.update(repos)
            }
            srl_repos.isRefreshing = false
        })

        viewModel.hasUsername.observe(this, Observer { hasUsername ->
            if (!hasUsername) {
                showStateInfo(
                    R.drawable.ic_nothing_searched_state,
                    R.string.tx_state_nothing_searched_title,
                    R.string.tx_state_nothing_searched_desc
                )
                srl_repos.isRefreshing = false
            }
        })

        viewModel.hasRepos.observe(this, Observer { hasRepos ->
            if (!hasRepos) {
                showStateInfo(
                    R.drawable.ic_no_repos_state,
                    R.string.tx_state_no_repos_title,
                    R.string.tx_state_no_repos_desc
                )
            }
        })

        viewModel.hasError.observe(this, Observer { hasError ->
            if (hasError) {
                showErrorInfo(viewModel.error.value!!)
                srl_repos.isRefreshing = false
            }
        })
    }

    private fun setupSearchView(searchView: SearchView) {
        searchView.maxWidth = Integer.MAX_VALUE
        searchView.queryHint = resources.getString(R.string.tx_search_view_hint)
        searchView.isIconified = true

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.search(query)
                invalidateOptionsMenu()
                return false
            }

            override fun onQueryTextChange(newText: String) = true
        })

        // Trick to change text color
        searchView.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
            .setTextColor(ResourcesCompat.getColor(resources, android.R.color.white, null))
    }

    private fun setupSwipeRefreshLayout() {
        srl_repos.setColorSchemeResources(R.color.colorPrimary)
        srl_repos.setOnRefreshListener {
            viewModel.refresh()
        }
    }

    private fun setupRecyclerView() {
        adapter = RepoSearchAdapter(this)
        rv_repos.adapter = adapter
        rv_repos.layoutManager = LinearLayoutManager(this)
    }

    private fun showErrorInfo(error: Error) {
        if (error.throwable != null) {
            when (error.throwable) {
                is UnknownHostException -> {
                    showStateInfo(
                        R.drawable.ic_user_not_found_state,
                        R.string.tx_state_network_error_title,
                        R.string.tx_state_network_error_desc
                    )
                }
                else -> {
                    showStateInfo(
                        R.drawable.ic_user_not_found_state,
                        R.string.tx_state_unknown_error_title,
                        R.string.tx_state_unknown_error_desc
                    )
                    Log.d(TAG, "Error: ${error.throwable}")
                }
            }

            return
        }

        when (error.code) {
            404 -> {
                showStateInfo(
                    R.drawable.ic_user_not_found_state,
                    R.string.tx_state_user_not_found_title,
                    R.string.tx_state_user_not_found_desc
                )
            }
            else -> {
                showStateInfo(
                    R.drawable.ic_user_not_found_state,
                    "Erro!",
                    error.message!!
                )
            }
        }
    }

    private fun showStateInfo(icRes: Int, titleRes: Int, descRes: Int) {
        showStateInfo(
            icRes,
            resources.getString(titleRes),
            resources.getString(descRes)
        )
    }

    private fun showStateInfo(icRes: Int, title: String, desc: String) {
        rv_repos.visibility = View.GONE

        iv_state_img.apply {
            visibility = View.VISIBLE
            setImageResource(icRes)
        }

        tv_state_title.apply {
            visibility = View.VISIBLE
            text = title
        }

        tv_state_desc.apply {
            visibility = View.VISIBLE
            text = desc
        }
    }

    private fun hideStateInfo() {
        iv_state_img.apply {
            visibility = View.GONE
            setImageDrawable(null)
        }

        tv_state_title.apply {
            visibility = View.GONE
            text = ""
        }

        tv_state_desc.apply {
            visibility = View.GONE
            text = ""
        }

        rv_repos.visibility = View.VISIBLE
    }

    companion object {
        private val TAG = RepoSearchActivity::class.java.simpleName
    }
}