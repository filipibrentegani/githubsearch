package com.filipibrentegani.githubsearch.findrepositoriesscreen.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.filipibrentegani.githubsearch.R
import com.filipibrentegani.githubsearch.models.RepositoryResponse
import com.filipibrentegani.githubsearch.databinding.MyItemLayoutBinding
import com.filipibrentegani.githubsearch.utils.setTextOrGone
import com.squareup.picasso.Picasso

class FindRepositoryItemHolder(private val viewBinding: MyItemLayoutBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

    fun bind(model: RepositoryResponse, position: Int) {
        viewBinding.tvRepoName.setTextOrGone(model.name)
        viewBinding.tvRepoFullName.setTextOrGone(model.fullName)
        Picasso.get().load(model.owner.avatarUrl).into(viewBinding.imOwnerAvatar)
        viewBinding.tvOwner.setTextOrGone(model.owner.login)
        viewBinding.tvHasWiki.visibility = if (model.hasWiki) View.VISIBLE else View.GONE
        viewBinding.tvLanguage.setTextOrGone(model.language)
        viewBinding.tvDescription.setTextOrGone(model.description)
        viewBinding.tvRepositoryUrl.setTextOrGone(model.repoUrl)
        viewBinding.tvHomePage.setTextOrGone(model.homepage)
        viewBinding.tvLicense.setTextOrGone(model.license?.name)
    }

    companion object {
        fun creteNewHolder(parent: ViewGroup): FindRepositoryItemHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.my_item_layout, parent, false)
            val binding = MyItemLayoutBinding.bind(view)
            return FindRepositoryItemHolder(binding)
        }
    }
}