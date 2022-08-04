package com.mihahoni.topnews.ui.newsSources

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mihahoni.topnews.R
import com.mihahoni.topnews.databinding.FragmentNewsSourcesBinding
import com.mihahoni.topnews.ui.base.BaseFragment
import com.mihahoni.topnews.utils.MarginItemDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsSourcesFragment : BaseFragment<FragmentNewsSourcesBinding>() {

    private lateinit var sourcesAdapter: NewsSourcesAdapter
    private val newsSourcesViewModel by viewModels<NewsSourcesViewModel>()
    override fun viewLayoutId(): Int = R.layout.fragment_news_sources


    override fun initViews() {
        sourcesAdapter = NewsSourcesAdapter()
        getViewDataBinding().newsSourceRecycleView.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            addItemDecoration(MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.margin_16dp)))
            adapter = sourcesAdapter
        }
    }
    override fun observeViewModel() {

        newsSourcesViewModel.getNewsSources().observe(viewLifecycleOwner) {
        sourcesAdapter.submitItems(it.sourcesList)
        }
    }


}