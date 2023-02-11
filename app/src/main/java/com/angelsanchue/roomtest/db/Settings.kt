package com.angelsanchue.roomtest.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "settings")
data class Settings(

    @PrimaryKey(autoGenerate = true)
    val id : Int?,

    @ColumnInfo("key")
    val key : String,

    @ColumnInfo("value")
    val value : String

)
