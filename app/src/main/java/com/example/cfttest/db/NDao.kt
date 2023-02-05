package com.example.cfttest.db


import androidx.room.*
import com.example.cfttest.model.Model


@Dao
interface NDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(n: Model)
    @Query("SELECT * FROM model")
    fun getAll(): List<Model>
    @Query("DELETE FROM model")
    fun clear()
}