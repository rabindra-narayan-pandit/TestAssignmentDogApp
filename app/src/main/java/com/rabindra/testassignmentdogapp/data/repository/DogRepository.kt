package com.rabindra.testassignmentdogapp.data.repository


import com.rabindra.testassignmentdogapp.data.local.DogDao
import com.rabindra.testassignmentdogapp.data.local.DogEntity
import com.rabindra.testassignmentdogapp.data.remote.DogApi
import com.rabindra.testassignmentdogapp.data.remote.DogResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DogRepository @Inject constructor(
    private val api: DogApi,
    private val dao: DogDao
) {
    fun getAllDogs(): Flow<List<DogEntity>> = dao.getAllDogs()

    suspend fun addDog(dogEntity: DogEntity) = dao.insert(dogEntity)

    suspend fun removeDog(dogId: String) = dao.deleteById(dogId)

    suspend fun fetchBreedDogImages(breed: String?): DogResponse {
        return api.getBreedDogImage(breed)
    }
}