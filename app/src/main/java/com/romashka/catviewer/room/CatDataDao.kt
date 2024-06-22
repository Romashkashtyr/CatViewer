package com.romashka.catviewer.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.romashka.catviewer.domain.model.CatData

@Dao
interface CatDataDao {
    @Query("SELECT * FROM catList")
    suspend fun getAll() : List<CatData>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(catDataInfo: CatData)

    @Delete
    suspend fun delete(catInfo: CatData)
}