package com.mihahoni.topnews.data.model

import com.google.gson.annotations.SerializedName

data class ArticleItem(
    @SerializedName("author")
    val author: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("published_at")
    val publishedAt: String,
    @SerializedName("urlToImage")
    val imageUrl: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("source")
    val source: SourceItem,
)
