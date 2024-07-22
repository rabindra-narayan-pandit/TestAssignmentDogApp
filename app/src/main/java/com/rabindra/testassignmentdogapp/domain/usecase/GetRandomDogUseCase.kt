package com.rabindra.testassignmentdogapp.domain.usecase


import com.rabindra.testassignmentdogapp.data.remote.DogApi
import com.rabindra.testassignmentdogapp.data.remote.DogResponse
import com.rabindra.testassignmentdogapp.data.remote.DogResponseImage
import javax.inject.Inject

class GetRandomDogUseCase @Inject constructor(
    private val dogApi: DogApi
) {
    suspend operator fun invoke(): DogResponseImage {
        return dogApi.getRandomDogImages()
    }
}