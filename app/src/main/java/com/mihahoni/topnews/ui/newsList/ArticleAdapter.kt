package com.mihahoni.topnews.ui.newsList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mihahoni.topnews.data.model.ArticleItem
import com.mihahoni.topnews.databinding.LayoutArticleItemBinding

class ArticleAdapter : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    private lateinit var listener: ArticleAdapterListener
    private var items: List<ArticleItem?> = ArrayList()

    fun submitItems(data: List<ArticleItem?>) {
        items = data
        notifyDataSetChanged()
    }

    fun setOnNewsClickListener(onSourceItemListener: ArticleAdapterListener) {
        listener = onSourceItemListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutArticleItemBinding.inflate(inflater)
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
        val binding: LayoutArticleItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(articleItem: ArticleItem?) {
            binding.article = articleItem
            itemView.setOnClickListener { listener.onArticleItemClicked(articleItem) }
        }
    }

    interface ArticleAdapterListener {
        fun onArticleItemClicked(articleItem: ArticleItem?)
    }
}