package com.romashka.catviewer.catadapter

import androidx.recyclerview.widget.DiffUtil
import com.romashka.catviewer.domain.model.CatData
import com.romashka.catviewer.room.datainterfaces.CatDataForRoom

class CatDataCallback() : DiffUtil.ItemCallback<CatDataForRoom>() {

    override fun areItemsTheSame(oldItem: CatDataForRoom, newItem: CatDataForRoom): Boolean {
        return oldItem.url == newItem.url && oldItem.fact == newItem.fact
    }

    override fun areContentsTheSame(oldItem: CatDataForRoom, newItem: CatDataForRoom): Boolean {
        return oldItem == newItem
    }
}