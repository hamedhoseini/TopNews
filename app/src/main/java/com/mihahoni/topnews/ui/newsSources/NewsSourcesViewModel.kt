package com.mihahoni.topnews.ui.newsSources

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.mihahoni.topnews.data.Result
import com.mihahoni.topnews.data.model.NewsSourceResponse
import com.mihahoni.topnews.data.repository.NewsRepository
import com.mihahoni.topnews.data.succeeded
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject


@HiltViewModel
class NewsSourcesViewModel @Inject constructor(
    private val newsRepository: NewsRepository
): ViewModel()  {

    fun getNewsSources() = liveData(Dispatchers.IO){

        val serviceFromRemote = newsRepository.getServiceFromRemote()
            if(serviceFromRemote is Result.Success){
                emit(serviceFromRemote.data)
            }
    }


}