package com.filipibrentegani.githubsearch.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FindRepositoriesResponse(
        val items: List<RepositoryResponse>,
        @SerialName("total_count") val totalItems: Int
)