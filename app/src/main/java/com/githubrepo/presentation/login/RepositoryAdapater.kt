package com.githubrepo.presentation.login

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.githubrepo.domain.entity.RepositoriesEntity
import com.google.android.material.textview.MaterialTextView
import com.iiab.mobilebanking.R

class RepositoryAdapater(
    private val repoList: List<RepositoriesEntity.RepositoryDetails>
) : RecyclerView.Adapter<RepositoryAdapater.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_repository_details, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val largeNews = repoList[position]
        holder.repoName.text = largeNews.name
    }

    override fun getItemCount(): Int = repoList.size

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val repoName: MaterialTextView = itemView.findViewById(R.id.repoName)
        val authorName: MaterialTextView = itemView.findViewById(R.id.authorName)
        val languages: MaterialTextView = itemView.findViewById(R.id.languages)

    }

}