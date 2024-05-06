package com.romashka.catviewer.catadapter

import androidx.recyclerview.widget.DiffUtil
import com.romashka.catviewer.domain.model.CatData

class CatDataCallback() : DiffUtil.ItemCallback<CatData>() {
    override fun areItemsTheSame(oldItem: CatData, newItem: CatData): Boolean {
        TODO("Not yet implemented")
    }

    override fun areContentsTheSame(oldItem: CatData, newItem: CatData): Boolean {
        TODO("Not yet implemented")
    }
}