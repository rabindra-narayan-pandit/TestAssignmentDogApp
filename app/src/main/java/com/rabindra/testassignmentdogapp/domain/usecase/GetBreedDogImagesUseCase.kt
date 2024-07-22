package com.rabindra.testassignmentdogapp.domain.usecase

import com.rabindra.testassignmentdogapp.data.remote.DogResponse
import com.rabindra.testassignmentdogapp.data.repository.DogRepository

class GetBreedDogImagesUseCase (private val repository: DogRepository) {

    suspend fun execute(breed: String): DogResponse {
        return repository.fetchBreedDogImages(breed)
    }
}