package com.angelsanchue.roomtest.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PersonDAO {

    @Insert
    suspend fun insertPerson(person: Person)

    @Delete
    suspend fun deletePerson(person: Person)

    @Query("Select * from persons")
    suspend fun getAllPersons() : List<Person>

}