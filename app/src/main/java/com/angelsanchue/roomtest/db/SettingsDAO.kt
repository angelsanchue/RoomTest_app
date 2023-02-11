package com.angelsanchue.roomtest.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SettingsDAO {

    @Insert
    suspend fun insertSetting(setting: Settings)

    @Query("Select * from settings")
    suspend fun getAllSettings() : List<Settings>

}