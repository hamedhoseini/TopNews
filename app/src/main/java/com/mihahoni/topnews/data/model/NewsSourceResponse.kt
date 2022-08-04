package com.mihahoni.topnews.data.model

import com.google.gson.annotations.SerializedName

data class NewsSourceResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("sources")
    val sourcesList: List<SourceItem>,
)
