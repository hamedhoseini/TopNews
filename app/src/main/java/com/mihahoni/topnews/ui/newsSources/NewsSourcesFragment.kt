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
import com.mihahoni.topnews.utils.StateHandler
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
        newsSourcesViewModel.newsSourcesList.observe(viewLifecycleOwner) { newsSourceResponse ->
            newsSourceResponse.let { sourcesAdapter.submitItems(newsSourceResponse) }
        }
        newsSourcesViewModel.newsSourcesFetchingState.observe(viewLifecycleOwner) { newsSourceFetchingState ->
            when (newsSourceFetchingState) {
                is StateHandler.Loading -> getViewDataBinding().isLoading = true
                is StateHandler.Success<*> -> getViewDataBinding().isLoading = false
                is StateHandler.Failure -> getViewDataBinding().isLoading = true
            }
        }
    }


}