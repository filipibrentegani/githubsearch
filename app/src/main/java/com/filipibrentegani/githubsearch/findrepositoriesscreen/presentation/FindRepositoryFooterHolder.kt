package com.filipibrentegani.githubsearch.findrepositoriesscreen.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.filipibrentegani.githubsearch.databinding.FooterLayoutBinding

class FindRepositoryFooterHolder(
        private val viewBinding: FooterLayoutBinding,
        private val retry: () -> Unit
) : RecyclerView.ViewHolder(viewBinding.root) {

    fun bind(loadState: LoadState) {
        viewBinding.btnRetry.setOnClickListener { retry.invoke() }
        if (loadState is LoadState.Error) {
            viewBinding.tvError.text = loadState.error.localizedMessage
        }
        viewBinding.pbLoading.isVisible = loadState is LoadState.Loading
        viewBinding.btnRetry.isVisible = loadState !is LoadState.Loading
        viewBinding.tvError.isVisible = loadState !is LoadState.Loading
    }

    companion object {
        fun creteNewHolder(
                parent: ViewGroup,
                retry: () -> Unit
        ): FindRepositoryFooterHolder {
            val binding = FooterLayoutBinding.inflate(LayoutInflater.from(parent.context))
            return FindRepositoryFooterHolder(binding, retry)
        }
    }
}