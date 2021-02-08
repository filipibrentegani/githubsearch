package com.filipibrentegani.githubsearch.findrepositoriesscreen.data

import com.filipibrentegani.githubsearch.models.FindRepositoriesResponse
import com.filipibrentegani.githubsearch.datasources.GitHubApi

class FindRepositoriesRepository(private val api: GitHubApi) {
    suspend fun getRepositories(text: String, itemsPerPage: Int, pageIndex: Int): FindRepositoriesResponse? {
        return api.getRepositories(text, itemsPerPage, pageIndex)
    }
}