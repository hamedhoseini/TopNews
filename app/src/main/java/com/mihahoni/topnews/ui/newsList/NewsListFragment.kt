package com.mihahoni.topnews.ui.newsList

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.mihahoni.topnews.R
import com.mihahoni.topnews.databinding.FragmentNewsListBinding
import com.mihahoni.topnews.ui.base.BaseFragment
import com.mihahoni.topnews.utils.MarginItemDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsListFragment : BaseFragment<FragmentNewsListBinding>() {

    private lateinit var sourceId: String
    private lateinit var newsAdapter: NewsAdapter
    private val newsViewModel by viewModels<NewsViewModel>()
    private val args: NewsListFragmentArgs by navArgs()

    override fun viewLayoutId(): Int = R.layout.fragment_news_list

    override fun observeViewModel() {
        newsViewModel.getNewsBySourceId(sourceId).observe(viewLifecycleOwner) {
            newsAdapter.submitItems(it.articleList)
        }
    }

    override fun initViews() {
        args.let {
            sourceId = it.sourceId
        }
        newsAdapter = NewsAdapter()
        getViewDataBinding().recycleViewNews.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            addItemDecoration(MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.margin_16dp)))
            adapter = newsAdapter
        }
    }

}