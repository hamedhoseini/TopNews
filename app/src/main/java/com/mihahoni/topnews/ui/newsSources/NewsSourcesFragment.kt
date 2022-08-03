package com.mihahoni.topnews.ui.newsSources

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mihahoni.topnews.R
import com.mihahoni.topnews.databinding.FragmentNewsSourcesBinding
import com.mihahoni.topnews.utils.MarginItemDecoration

class NewsSourcesFragment : Fragment() {

    private lateinit var binding: FragmentNewsSourcesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsSourcesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initNewsSourceRecycleView()
    }

    private fun initNewsSourceRecycleView() {
        val sourcesAdapter = NewsSourcesAdapter()
        binding.newsSourceRecycleView.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            addItemDecoration(MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.margin_16dp)))
            adapter = sourcesAdapter
        }
//        sourcesAdapter.submitItems(arrayListOf("hi", "salam", "so on"))
    }
}