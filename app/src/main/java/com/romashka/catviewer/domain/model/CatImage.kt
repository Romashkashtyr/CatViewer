package com.romashka.catviewer.domain

import com.romashka.catviewer.room.datainterfaces.CatImageDataInterface
import com.squareup.moshi.Json


data class CatImage(
    @Json(name = "url") override val url: String
) : CatImageDataInterface