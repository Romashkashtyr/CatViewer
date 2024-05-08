package com.romashka.catviewer.domain

import com.romashka.catviewer.room.datainterfaces.CatFactDataInterface

data class CatFactResponse(
    override val fact: String
) : CatFactDataInterface


