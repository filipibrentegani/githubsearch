package com.filipibrentegani.githubsearch.findrepositoriesscreen.presentation

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.filipibrentegani.githubsearch.models.RepositoryResponse
import com.filipibrentegani.githubsearch.datasources.GitHubApi
import retrofit2.HttpException
import java.io.IOException

class FindRepositoriesPagingSource(private val api: GitHubApi, private val text: String) : PagingSource<Int, RepositoryResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepositoryResponse> {
        val position = params.key ?: 1
        return try {
            val result = api.getRepositories(text, params.loadSize, position)
            val nextKey = if (result?.items?.isEmpty() != false) {
                null
            } else {
                position + (result.items?.size / params.loadSize)
            }
            LoadResult.Page(result?.items ?: emptyList(), if (position == 1) null else position - 1, if (nextKey == position) null else nextKey)
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, RepositoryResponse>): Int? {
        return state.anchorPosition
    }
}