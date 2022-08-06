package com.mihahoni.topnews.ui.newsList

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mihahoni.topnews.data.model.ArticleItem
import com.mihahoni.topnews.data.repository.NewsRepository
import com.mihahoni.topnews.utils.StateHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val _articleList by lazy { MutableLiveData<List<ArticleItem>>() }
    val articleList: LiveData<List<ArticleItem>> by lazy { _articleList }

    private val _articleFetchingState by lazy { MutableLiveData<StateHandler>(StateHandler.Loading) }
    val articleFetchingState: LiveData<StateHandler> by lazy { _articleFetchingState }

    @SuppressLint("CheckResult")
    fun getArticleBySourceId(sourceId: String) {
        newsRepository.getArticleBySourceId(sourceId).subscribe({
            _articleList.value = it
            _articleFetchingState.value = StateHandler.Success(it)
        }, {
            _articleFetchingState.value = StateHandler.Failure(it.message)
        })
    }
}