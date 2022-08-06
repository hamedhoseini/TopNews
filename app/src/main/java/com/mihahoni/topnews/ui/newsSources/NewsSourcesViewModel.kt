package com.mihahoni.topnews.ui.newsSources

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mihahoni.topnews.data.model.SourceItem
import com.mihahoni.topnews.data.repository.NewsRepository
import com.mihahoni.topnews.utils.StateHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class NewsSourcesViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    init {
        getNewsSources()
    }

    private val _newsSourcesList by lazy { MutableLiveData<List<SourceItem>>() }
    val newsSourcesList: LiveData<List<SourceItem>> by lazy { _newsSourcesList }

    private val _newsSourcesFetchingState by lazy { MutableLiveData<StateHandler>(StateHandler.Loading) }
    val newsSourcesFetchingState: LiveData<StateHandler> by lazy { _newsSourcesFetchingState }

    @SuppressLint("CheckResult")
    fun getNewsSources() {
        newsRepository.getSources().subscribe({
            _newsSourcesList.value = it
            _newsSourcesFetchingState.value = StateHandler.Success(it)
        }, {
            _newsSourcesFetchingState.value = StateHandler.Failure(it.message)
        })
    }
}