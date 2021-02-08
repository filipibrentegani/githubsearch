package com.filipibrentegani.githubsearch.findrepositoriesscreen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.filipibrentegani.githubsearch.datasources.GitHubApi
import com.filipibrentegani.githubsearch.models.RepositoryResponse
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

class FindRepositoriesViewModel: ViewModel() {

    lateinit var retrofit: Retrofit

    fun init() {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        //TODO condição para logar apenas em DEBUG
        //TODO mover criação de retrofit client para local adequado


        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        val contentType = "application/json".toMediaType()
        retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(Json{ignoreUnknownKeys = true; coerceInputValues = true}.asConverterFactory(contentType))
            .client(client)
            .build()
    }

    fun getItems(text: String): Flow<PagingData<RepositoryResponse>> {
        return Pager(
            config = PagingConfig(pageSize = 30, enablePlaceholders = false),
            pagingSourceFactory = {
                FindRepositoriesPagingSource(
                    api = retrofit.create(GitHubApi::class.java),
                    text
                )
            }
        ).flow.cachedIn(viewModelScope)
    }
}