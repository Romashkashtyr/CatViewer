package com.romashka.catviewer.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.romashka.catviewer.room.datainterfaces.CatFactDataInterface
import com.romashka.catviewer.room.datainterfaces.CatImageDataInterface
import com.squareup.moshi.Json

@Entity(tableName = "catList")
data class CatData(
    @ColumnInfo(name = "fact_name") @Json(name = "fact") override var fact : String,
    @ColumnInfo(name = "imageUrl_name") @Json(name = "url") override var url : String,
    @PrimaryKey(autoGenerate = true) var id: Int = 0
) : CatFactDataInterface, CatImageDataInterface
