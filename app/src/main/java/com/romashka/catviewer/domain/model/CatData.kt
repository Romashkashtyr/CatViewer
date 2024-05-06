package com.romashka.catviewer.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "catList")
data class CatData(
   @ColumnInfo(name = "fact_name") @Json(name = "fact") var fact : String,
   @ColumnInfo(name = "imageUrl_name") @Json(name = "url") var url : String,
    @PrimaryKey var id: Int = 0
)
