package com.rabindra.testassignmentdogapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DogApi {
    @GET("breeds/image/random")
    suspend fun getRandomDogImage(): DogResponse

    @GET("breeds/image/random/30")
    suspend fun getRandomDogImages(): DogResponseImage

    @GET("breed/{breed}/images/random/5")
    suspend fun getBreedDogImage(@Path("breed") breed: String?): DogResponse
}