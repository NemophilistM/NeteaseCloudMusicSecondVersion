package com.example.neteasecloudmusicsecondversionapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.neteasecloudmusicsecondversionapplication.R


class SearchListFooterAdapter(val retry : ()->Unit) : LoadStateAdapter<SearchListFooterAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val progressBar: ProgressBar = itemView.findViewById(R.id.pb_search_footer)
        val retryButton: Button = itemView.findViewById(R.id.bt_retry)
    }

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        holder.progressBar.isVisible = loadState is LoadState.Loading
        holder.retryButton.isVisible = loadState is LoadState.Error
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_seach_footer, parent, false)
        val holder = ViewHolder(view)
        holder.retryButton.setOnClickListener {
           retry()
        }
        return holder
    }

}