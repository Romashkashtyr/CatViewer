package com.romashka.catviewer.domain.model

import com.squareup.moshi.Json


data class CatData(
    @Json(name = "fact") var fact : String,
    @Json(name = "url") var url : String
)
