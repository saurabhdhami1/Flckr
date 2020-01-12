package com.example.saurabh.flckr.ui.main.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.saurabh.flckr.models.PhotoSizes
import com.example.saurabh.flckr.R
import kotlinx.android.synthetic.main.image_item.view.*

class FlckrImagesAdapter : RecyclerView.Adapter<FlckrImagesAdapter.ViewHolder>() {
    var list = mutableListOf<PhotoSizes>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) = with(itemView) {
            val photo = list[position]
            Glide.with(context)
                .load(photo.source)
                .override(photo.width ?: 0, photo.height ?: 0)
                .into(flckrImage)
        }
    }
}