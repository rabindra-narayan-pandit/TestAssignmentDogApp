package com.rabindra.testassignmentdogapp.domain.usecase

import com.rabindra.testassignmentdogapp.data.local.DogEntity
import com.rabindra.testassignmentdogapp.data.remote.DogResponse
import com.rabindra.testassignmentdogapp.data.repository.DogRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DogUseCase @Inject constructor(
    private val repository: DogRepository
) {
    fun getAllDogs(): Flow<List<DogEntity>> = repository.getAllDogs()

    suspend fun addDog(dogEntity: DogEntity) = repository.addDog(dogEntity)

    suspend fun removeDog(dogId: String) = repository.removeDog(dogId)


}