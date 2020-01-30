package com.upco.octo.presentation.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.upco.octo.R
import com.upco.octo.domain.entity.Repository
import kotlinx.android.synthetic.main.item_repository.view.*

class RepositorySearchAdapter(private val repositories: List<Repository>):
    RecyclerView.Adapter<RepositorySearchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_repository, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(repositories[position])
    }

    override fun getItemCount() = repositories.size

    inner class ViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

        fun bind(repository: Repository) {
            view.tv_repository_name.text = repository.name
            view.tv_repository_desc.text = repository.description
            view.tv_repository_stargazers.text = repository.stargazersCount.toString()
            view.tv_repository_forks.text = repository.forksCount.toString()
            view.tv_repository_lang.text = repository.language
        }
    }
}