package com.mihahoni.topnews.ui.newsSources

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mihahoni.topnews.data.model.SourceItem
import com.mihahoni.topnews.databinding.LayoutNewsSourceItemBinding

class NewsSourcesAdapter : RecyclerView.Adapter<NewsSourcesAdapter.ViewHolder>() {

    private lateinit var listener: SourceAdapterListener
    private var items: List<SourceItem?> = ArrayList()

    fun submitItems(data: List<SourceItem>) {
        items = data
        notifyDataSetChanged()
    }

    fun setOnSourceClickListener(onSourceItemListener: SourceAdapterListener) {
        listener = onSourceItemListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutNewsSourceItemBinding.inflate(inflater)
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
        val binding: LayoutNewsSourceItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(sourceItem: SourceItem?) {
            binding.source = sourceItem
            itemView.setOnClickListener { listener.onNewsSourceClicked(sourceItem) }
        }
    }

    interface SourceAdapterListener {
        fun onNewsSourceClicked(source: SourceItem?)
    }
}