package com.mihahoni.topnews.ui.newsSources

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.mihahoni.topnews.data.Result
import com.mihahoni.topnews.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject


@HiltViewModel
class NewsSourcesViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    fun getNewsSources() = liveData(Dispatchers.IO) {
        emit(Result.Loading)
        val serviceFromRemote = newsRepository.getSources()
        emit(serviceFromRemote)
    }


}