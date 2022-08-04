package com.mihahoni.topnews.ui.newsList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mihahoni.topnews.data.model.ArticleItem
import com.mihahoni.topnews.databinding.LayoutNewsItemBinding

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private lateinit var listener: NewsAdapterListener
    private var items: List<ArticleItem?> = ArrayList()

    fun submitItems(data: List<ArticleItem?>) {
        items = data
        notifyDataSetChanged()
    }

    fun setOnNewsClickListener(onSourceItemListener: NewsAdapterListener) {
        listener = onSourceItemListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutNewsItemBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(items[position])

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(
        val binding: LayoutNewsItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(articleItem: ArticleItem?) {
            binding.article = articleItem
            itemView.setOnClickListener { listener.onNewsItemClicked(articleItem) }
        }
    }

    interface NewsAdapterListener {
        fun onNewsItemClicked(source: ArticleItem?)
    }
}