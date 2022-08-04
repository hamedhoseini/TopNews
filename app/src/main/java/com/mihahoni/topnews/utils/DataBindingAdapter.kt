package com.mihahoni.topnews.utils

import android.net.Uri
import android.text.TextUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.mihahoni.topnews.R
import java.util.*

@BindingAdapter("category","country")
fun setCategory(textView: TextView, category: String, country: String) {

    val textViewString = StringBuilder()

    val upperCaseCategory = if (TextUtils.isEmpty(category)) category else category.substring(0, 1)
        .uppercase(Locale.getDefault()) + category.substring(1)

    textViewString.append(upperCaseCategory)
    if (!TextUtils.isEmpty(category) && !TextUtils.isEmpty(country)) {
        textViewString.append(" - ")
    }

    val locale = Locale("en", country)
    textViewString.append(locale.displayCountry)
    textView.text = textViewString
}

@BindingAdapter("sourceUrl")
fun loadSourceImage(imageView: ImageView, sourceUrl: String?) {
    var sourceUrl = sourceUrl
    val context = imageView.context
    val iconUrl = "https://besticon.herokuapp.com/icon?url=%s&size=80..120..200"
    sourceUrl = String.format(iconUrl, Uri.parse(sourceUrl).authority)
    Glide.with(imageView)
        .load(sourceUrl)
        .placeholder(context.resources.getDrawable(R.drawable.ic_default_image))
        .into(imageView)
}