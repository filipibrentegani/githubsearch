package com.filipibrentegani.githubsearch.models

import kotlinx.serialization.Serializable

@Serializable
data class LicenseResponse (
        val key: String,
        val name: String,
        val url: String = "",
)