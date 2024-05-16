package com.romashka.catviewer.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.romashka.catviewer.domain.model.CatData

@Dao
interface CatDataDao {
    @Query("SELECT * FROM catList")
    fun getAll() : LiveData<List<CatData>>

    @Insert
    suspend fun insertAll(catDataInfo: CatData)

    @Delete
    suspend fun delete(catInfo: CatData)
}