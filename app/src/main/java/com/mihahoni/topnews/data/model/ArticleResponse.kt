package com.mihahoni.topnews.data.model

import com.google.gson.annotations.SerializedName

data class ArticleResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int,
    @SerializedName("articles")
    val articleList: List<ArticleItem>,
)
