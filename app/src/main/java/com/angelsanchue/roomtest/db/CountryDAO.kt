package com.angelsanchue.roomtest.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CountryDAO {
    @Insert
    suspend fun insertCountry(vararg country : Country)

    @Delete
    suspend fun deleteCountry(country: Country)

    @Query("Select * from countries")
    suspend fun getAllCountries() : List<Country>

    @Query("Select name from countries")
    suspend fun getAllNameCountries() : List<String>

}