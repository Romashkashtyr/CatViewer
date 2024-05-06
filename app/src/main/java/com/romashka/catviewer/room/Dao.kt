package com.romashka.catviewer.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.romashka.catviewer.domain.model.CatData

@Dao
interface Dao {
    @Query("SELECT * FROM catList")
    fun getAll() : List<CatData>

    @Insert
    fun insertAll(vararg catDataInfo: CatData)

    @Delete
    fun delete(catInfo: CatData)
}