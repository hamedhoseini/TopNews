package com.mihahoni.topnews.data.repository

import com.mihahoni.topnews.data.Result
import com.mihahoni.topnews.data.model.NewsSourceResponse

interface NewsRepository {
   suspend fun getServiceFromRemote(): Result<NewsSourceResponse>
}