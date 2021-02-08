package com.filipibrentegani.githubsearch.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RepositoryResponse(
        val id: Int,
        val name: String,
        @SerialName("full_name") val fullName: String,
        val owner: OwnerResponse,
        val description: String = "",
        @SerialName("svn_url") val repoUrl: String,
        val homepage: String = "",
        val language: String = "",
        @SerialName("has_wiki") val hasWiki: Boolean,
        val license: LicenseResponse? = null,
)





