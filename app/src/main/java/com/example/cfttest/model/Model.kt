package com.example.cfttest.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Model(
    @ColumnInfo
    val number: String,

    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
    ){

}
