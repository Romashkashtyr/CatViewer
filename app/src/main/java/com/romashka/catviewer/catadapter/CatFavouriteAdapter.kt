package com.romashka.catviewer.catadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.romashka.catviewer.databinding.FavouriteItemBinding
import com.romashka.catviewer.domain.model.CatData

class CatFavouriteAdapter(val catDataList : List<CatData>)
    : ListAdapter<CatData, CatFavouriteAdapter.CatFavouriteViewHolder>(CatDataCallback()) {


    inner class CatFavouriteViewHolder(binding: FavouriteItemBinding ) : RecyclerView.ViewHolder(binding.root) {
        private val imageViewFav = binding.imageSaved
        private val textViewFav = binding.textViewSaved

        fun bind() {
            val items = catDataList[adapterPosition]
            itemView.apply {
                Glide.with(context)
                    .load(items.url)
                    .into(imageViewFav)
                textViewFav.text = items.fact
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatFavouriteViewHolder {
        val view = FavouriteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CatFavouriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: CatFavouriteViewHolder, position: Int) {
        holder.bind()

    }

}