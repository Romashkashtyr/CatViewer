package com.romashka.catviewer.presentation.catadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.romashka.catviewer.databinding.FavouriteItemBinding
import com.romashka.catviewer.domain.model.CatData

class CatFavouriteAdapter(val catDataList : ArrayList<CatData>, /*val deleteDataByClickInterfaceAdapter: DeleteDataByClickInterface*/) : RecyclerView.Adapter<CatFavouriteAdapter.CatFavouriteViewHolder>() {



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
            itemView.setOnLongClickListener {
                //deleteDataByClickInterfaceAdapter
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatFavouriteViewHolder {
        val view = FavouriteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CatFavouriteViewHolder(view)
    }

    override fun getItemCount(): Int {
        return catDataList.size
    }

    override fun onBindViewHolder(holder: CatFavouriteViewHolder, position: Int) {
        holder.bind()
        notifyItemChanged(holder.adapterPosition)
        updateList(catDataList)
    }

    private fun updateList(newList: List<CatData>) {
        catDataList.clear()

        catDataList.addAll(newList)

    }

    interface DeleteDataByClickInterface {
        fun deleteDataByClickInterface(data: CatData)
    }


}