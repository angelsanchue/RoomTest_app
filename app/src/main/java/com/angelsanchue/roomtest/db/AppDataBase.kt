package com.angelsanchue.roomtest.db

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Country::class,Person::class,Settings::class], version = 2, autoMigrations = [AutoMigration(from = 1, to = 2)])


abstract class AppDataBase : RoomDatabase() {

    abstract fun getCountryDAO() : CountryDAO

    abstract  fun getPersonDAO() : PersonDAO

    abstract  fun getSettingDAO() : SettingsDAO


}