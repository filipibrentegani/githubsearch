package com.filipibrentegani.githubsearch.datasources

import com.filipibrentegani.githubsearch.models.FindRepositoriesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApi {
    @GET("search/repositories")
    suspend fun getRepositories(
        @Query("q") text: String,
        @Query("per_page") itemsPerPage: Int,
        @Query("page") page: Int
    ): FindRepositoriesResponse?
}