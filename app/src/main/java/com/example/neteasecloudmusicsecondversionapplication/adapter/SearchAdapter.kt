package com.example.neteasecloudmusicsecondversionapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.musiclibrary.entity.Ar
import com.example.musiclibrary.entity.Song
import com.example.neteasecloudmusicsecondversionapplication.R
import com.squareup.picasso.Picasso

class SearchAdapter(private val click:(Song)->Unit) : PagingDataAdapter<Song,SearchAdapter.ViewHolder>(COMPARATOR) {
    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Song>() {
            override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean {
                return oldItem == newItem
            }
        }
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tv_song_name)
        val tvAlbumName: TextView = itemView.findViewById(R.id.tv_album)
        val tvAuthor: TextView = itemView.findViewById(R.id.tv_author)
        val ivAlbumPic : ImageView = itemView.findViewById(R.id.iv_pic_album)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_song_show, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val song = getItem(position)
        if (song != null) {
            holder.tvName.text = song.name
            holder.tvAlbumName.text = song.al.name
            val artistName = StringBuffer(" ")
            for (i in 0 until song.ar.size) {
                val artist: Ar = song.ar[i]
                val artName: String = artist.name
                artistName.append(artName).append(" ")
            }
            holder.tvAuthor.text = artistName
            Picasso.with(holder.itemView.context).load(song.al.picUrl).placeholder(R.drawable.img_wait).error(R.drawable.img_error).into(holder.ivAlbumPic)
            holder.itemView.setOnClickListener {
                click(song)
            }
        }
    }

}