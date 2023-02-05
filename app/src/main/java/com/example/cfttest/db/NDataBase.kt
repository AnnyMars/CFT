package com.example.cfttest.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cfttest.model.Model

@Database(entities = [Model::class], version = 1, exportSchema = false)
abstract class NDataBase: RoomDatabase() {
    abstract fun getDataBaseDao(): NDao
    companion object{
        @Volatile
        private var INSTANCE: NDataBase? = null
        fun getInstance(context: Context): NDataBase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext,
                        NDataBase::class.java, "table")
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}