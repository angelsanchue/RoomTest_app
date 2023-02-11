package com.angelsanchue.roomtest.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity (tableName = "persons",
        foreignKeys = [ForeignKey (entity = Country::class, parentColumns = ["id"], childColumns = ["id_country"])])

data class Person (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id : Int,
    @ColumnInfo(name = "name")
    val name : String,
    @ColumnInfo(name = "last_name")
    val lastName : String,
    @ColumnInfo(name = "id_country")
    val idCountry : Int

        )
