package com.filipibrentegani.githubsearch.findrepositoriesscreen.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.filipibrentegani.githubsearch.utils.KeyboardUtils
import com.filipibrentegani.githubsearch.databinding.ActivityMainBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FindRepositoriesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: FindRepositoriesViewModel by viewModel()
    private val adapter = FindRepositoriesAdapter()
    private var searchJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.init()

        binding.btnTryAgain.setOnClickListener { adapter.retry() }

        binding.rvList.layoutManager = LinearLayoutManager(this)
        binding.rvList.adapter = adapter.withLoadStateHeaderAndFooter(
            header = FindRepositoriesLoadAdapter { adapter.retry() },
            footer = FindRepositoriesLoadAdapter { adapter.retry() }
        )
        adapter.addLoadStateListener { loadState ->
            binding.rvList.isVisible = loadState.source.refresh is LoadState.NotLoading
            binding.pbLoading.isVisible = loadState.source.refresh is LoadState.Loading
            binding.btnTryAgain.isVisible = loadState.source.refresh is LoadState.Error
        }
        binding.etFind.setOnEditorActionListener { _, action, _ ->
            if (action == EditorInfo.IME_ACTION_SEARCH) {
                search()
                true
            } else {
                false
            }
        }
        binding.ivFind.setOnClickListener {
            search()
        }

        lifecycleScope.launch {
            adapter.loadStateFlow
                .distinctUntilChangedBy {
                    it.refresh
                }
                .filter {
                    it.refresh is LoadState.NotLoading
                }
                .collect {
                    binding.rvList.scrollToPosition(0)
                }
        }

    }

    private fun search() {
        binding.etFind.text?.trim()?.let {
            if (it.isNotEmpty()) {
                searchJob?.cancel()
                searchJob = lifecycleScope.launch {
                    viewModel.getItems(it.toString()).collectLatest { pagingData ->
                        adapter.submitData(pagingData)
                    }
                }
            }
        }
        KeyboardUtils.hideKeyboard(this)
    }
}