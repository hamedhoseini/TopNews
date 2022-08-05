package com.mihahoni.topnews.ui.newsSources

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mihahoni.topnews.R
import com.mihahoni.topnews.data.Result
import com.mihahoni.topnews.data.model.SourceItem
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
        sourcesAdapter.setOnSourceClickListener(object : NewsSourcesAdapter.SourceAdapterListener {
            override fun onNewsSourceClicked(source: SourceItem?) {
                source?.let {
                    findNavController().navigate(
                        NewsSourcesFragmentDirections.actionNewsSourcesFragmentToNewsListFragment(
                            source.id
                        )
                    )
                }
            }

        })
    }

    override fun observeViewModel() {
        newsSourcesViewModel.getNewsSources().observe(viewLifecycleOwner) { newsSourceResponse ->
            when (newsSourceResponse) {
                is Result.Loading -> getViewDataBinding().isLoading = true
                is Result.Success -> {
                    getViewDataBinding().isLoading = false
                    newsSourceResponse.let { sourcesAdapter.submitItems(newsSourceResponse.data) }
                }
                is Result.Error -> getViewDataBinding().isLoading = true

            }
        }
    }


}