package com.romashka.catviewer.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.romashka.catviewer.domain.model.CatData
import com.romashka.catviewer.room.datainterfaces.CatDataForRoom

@Dao
interface CatDataDao {
    @Query("SELECT * FROM catList")
    fun getAll() : LiveData<List<CatDataForRoom>>

    @Insert
    suspend fun insertAll(catDataInfo: CatDataForRoom)

    @Delete
    suspend fun delete(catInfo: CatDataForRoom)
}