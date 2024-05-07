package com.romashka.catviewer.catadapter

import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.romashka.catviewer.R
import com.romashka.catviewer.databinding.ActivityFavouriteBinding
import com.romashka.catviewer.databinding.FavouriteItemBinding
import com.romashka.catviewer.domain.model.CatData

class CatFavouriteAdapter(val catDataList : List<CatData>)
    : ListAdapter<CatData, CatFavouriteAdapter.CatFavouriteViewHolder>(CatDataCallback()) {


    inner class CatFavouriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageViewFav = itemView.findViewById<ImageView>(R.id.image_saved)
        val textViewFav = itemView.findViewById<TextView>(R.id.text_view_saved)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatFavouriteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.favourite_item, parent, false)
        return CatFavouriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: CatFavouriteViewHolder, position: Int) {

        val context = holder.itemView.context

            val items = catDataList[position]
            holder.apply {
                Glide.with(context.applicationContext)
                    .load(items.url)
                    .into(imageViewFav)
                textViewFav.text = items.fact
            }


    }

}