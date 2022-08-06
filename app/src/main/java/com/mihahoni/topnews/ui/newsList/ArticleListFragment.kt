package com.mihahoni.topnews.ui.newsList

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.mihahoni.topnews.R
import com.mihahoni.topnews.databinding.FragmentArticleListBinding
import com.mihahoni.topnews.ui.base.BaseFragment
import com.mihahoni.topnews.utils.MarginItemDecoration
import com.mihahoni.topnews.utils.StateHandler
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleListFragment : BaseFragment<FragmentArticleListBinding>() {

    private lateinit var sourceId: String
    private lateinit var articleAdapter: ArticleAdapter
    private val articleViewModel by viewModels<ArticleViewModel>()
    private val args: ArticleListFragmentArgs by navArgs()

    override fun viewLayoutId(): Int = R.layout.fragment_article_list

    override fun observeViewModel() {
        articleViewModel.articleList.observe(viewLifecycleOwner) { articlesResponse ->
            articlesResponse.let {
                articleAdapter.submitItems(articlesResponse)
            }
        }
        articleViewModel.articleFetchingState.observe(viewLifecycleOwner) { newsSourceFetchingState ->
            when (newsSourceFetchingState) {
                is StateHandler.Loading -> getViewDataBinding().isLoading = true
                is StateHandler.Success<*> -> getViewDataBinding().isLoading = false
                is StateHandler.Failure -> getViewDataBinding().isLoading = true
            }
        }
    }

    override fun initViews() {
        args.let {
            sourceId = it.sourceId
        }
        articleAdapter = ArticleAdapter()
        getViewDataBinding().recycleViewNews.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            addItemDecoration(MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.margin_16dp)))
            adapter = articleAdapter
        }
        articleViewModel.getArticleBySourceId(sourceId)
    }

}