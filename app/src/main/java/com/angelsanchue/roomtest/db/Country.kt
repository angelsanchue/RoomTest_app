package com.angelsanchue.roomtest.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countries")
data class Country(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    @ColumnInfo(name = "name")
    val name : String
)
