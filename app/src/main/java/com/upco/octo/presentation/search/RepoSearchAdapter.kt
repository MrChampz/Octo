package com.upco.octo.presentation.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.upco.octo.R
import com.upco.domain.Repo
import kotlinx.android.synthetic.main.item_repo.view.*

class RepoSearchAdapter(private val listener: OnRepoClickListener):
    RecyclerView.Adapter<RepoSearchAdapter.ViewHolder>() {

    private val repos: ArrayList<Repo> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_repo, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(repos[position])
    }

    override fun getItemCount() = repos.size

    fun update(newRepos: List<Repo>) {
        repos.clear()
        repos.addAll(newRepos)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

        fun bind(repo: Repo) {
            view.tv_repository_name.text = repo.name
            view.tv_repository_stargazers.text = repo.stargazersCount.toString()
            view.tv_repository_forks.text = repo.forksCount.toString()

            if (repo.description.isNullOrEmpty()) {
                view.tv_repository_desc.visibility = View.GONE
            } else {
                view.tv_repository_desc.text = repo.description
                view.tv_repository_desc.visibility = View.VISIBLE
            }

            if (repo.language.isNullOrEmpty()) {
                view.tv_repository_lang.visibility = View.GONE
            } else {
                view.tv_repository_lang.text = repo.language
                view.tv_repository_lang.visibility = View.VISIBLE
            }

            view.setOnClickListener {
                listener.onRepoClick(repo)
            }
        }
    }
}