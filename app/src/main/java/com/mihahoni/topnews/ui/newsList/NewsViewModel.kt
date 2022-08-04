package com.mihahoni.topnews.ui.newsList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.mihahoni.topnews.data.Result
import com.mihahoni.topnews.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject


@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    fun getNewsBySourceId(sourceId: String) = liveData(Dispatchers.IO) {

        val serviceFromRemote = newsRepository.getNewsBySourceId(sourceId)
        if (serviceFromRemote is Result.Success) {
            emit(serviceFromRemote.data)
        }
    }
}