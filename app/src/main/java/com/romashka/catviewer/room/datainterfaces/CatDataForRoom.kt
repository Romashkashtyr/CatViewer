package com.romashka.catviewer.room.datainterfaces

import com.romashka.catviewer.domain.CatFactResponse
import com.romashka.catviewer.domain.CatImage

data class CatDataForRoom(val fact: CatFactResponse, val url: CatImage)
