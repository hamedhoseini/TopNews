package com.mihahoni.topnews.ui.newsList

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.mihahoni.topnews.R
import com.mihahoni.topnews.databinding.FragmentArticleListBinding
import com.mihahoni.topnews.ui.base.BaseFragment
import com.mihahoni.topnews.utils.MarginItemDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleListFragment : BaseFragment<FragmentArticleListBinding>() {

    private lateinit var sourceId: String
    private lateinit var articleAdapter: ArticleAdapter
    private val articleViewModel by viewModels<ArticleViewModel>()
    private val args: ArticleListFragmentArgs by navArgs()

    override fun viewLayoutId(): Int = R.layout.fragment_article_list

    override fun observeViewModel() {
        articleViewModel.getArticleBySourceId(sourceId).observe(viewLifecycleOwner) {
            articleAdapter.submitItems(it)
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
    }

}