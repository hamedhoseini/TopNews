package com.mihahoni.topnews.ui.newsList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.mihahoni.topnews.data.Result
import com.mihahoni.topnews.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject


@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    fun getArticleBySourceId(sourceId: String) = liveData(Dispatchers.IO) {
        emit(Result.Loading)
        val serviceFromRemote = newsRepository.getArticleBySourceId(sourceId)
        emit(serviceFromRemote)
    }
}