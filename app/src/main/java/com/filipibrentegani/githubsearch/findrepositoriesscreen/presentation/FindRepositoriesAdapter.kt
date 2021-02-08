package com.filipibrentegani.githubsearch.findrepositoriesscreen.presentation

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.filipibrentegani.githubsearch.models.RepositoryResponse

class FindRepositoriesAdapter : PagingDataAdapter<RepositoryResponse, FindRepositoryItemHolder>(DiffUtilCallBack()) {

    override fun onBindViewHolder(holder: FindRepositoryItemHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it, position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FindRepositoryItemHolder {
        return FindRepositoryItemHolder.creteNewHolder(parent)
    }
}

class FindRepositoriesLoadAdapter(private val retry: () -> Unit) : LoadStateAdapter<FindRepositoryFooterHolder>() {
    override fun onBindViewHolder(holder: FindRepositoryFooterHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): FindRepositoryFooterHolder {
        return FindRepositoryFooterHolder.creteNewHolder(parent, retry)
    }
}

class DiffUtilCallBack : DiffUtil.ItemCallback<RepositoryResponse>() {
    override fun areItemsTheSame(oldItem: RepositoryResponse, newItem: RepositoryResponse): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: RepositoryResponse, newItem: RepositoryResponse): Boolean {
        return oldItem == newItem
    }
}