package com.filipibrentegani.githubsearch.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OwnerResponse (
        val login: String,
        @SerialName("avatar_url") val avatarUrl: String,
        @SerialName("html_url") val userUrl: String,
        @SerialName("repos_url") val reposUrl: String,
        val type: String,
)