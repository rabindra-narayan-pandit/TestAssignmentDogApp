package com.rabindra.testassignmentdogapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DogDao {
    @Insert
    suspend fun insert(dog: DogEntity)

    @Query("SELECT * FROM dog_table")
    fun getAllDogs(): Flow<List<DogEntity>>

    @Query("DELETE FROM dog_table WHERE id = :dogId")
    suspend fun deleteById(dogId: String)
}