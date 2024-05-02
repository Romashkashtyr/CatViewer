package com.romashka.catviewer.domain

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json


data class CatData(
    @Json(name = "fact") var fact : String,
    @Json(name = "url") var url : String
)
