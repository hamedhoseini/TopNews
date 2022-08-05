package com.mihahoni.topnews.data.repository

import com.mihahoni.topnews.data.Result
import com.mihahoni.topnews.data.model.ArticleItem
import com.mihahoni.topnews.data.model.SourceItem

interface NewsRepository {
   suspend fun getSources(): Result<List<SourceItem>>
   suspend fun getArticleBySourceId(sourceId:String): Result<List<ArticleItem>>
}