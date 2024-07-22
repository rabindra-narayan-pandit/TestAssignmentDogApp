package com.rabindra.testassignmentdogapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dog_table")
data class DogEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val imageUrl: String
)
