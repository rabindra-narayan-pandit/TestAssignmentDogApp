package com.rabindra.testassignmentdogapp.di

import android.app.Application
import androidx.room.Room
import com.rabindra.testassignmentdogapp.data.local.DogDao
import com.rabindra.testassignmentdogapp.data.local.DogDatabase
import com.rabindra.testassignmentdogapp.data.remote.DogApi
import com.rabindra.testassignmentdogapp.data.repository.DogRepository
import com.rabindra.testassignmentdogapp.domain.usecase.DogUseCase
import com.rabindra.testassignmentdogapp.domain.usecase.GetBreedDogImagesUseCase
import com.rabindra.testassignmentdogapp.domain.usecase.GetRandomDogUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDogDatabase(app: Application): DogDatabase =
        Room.databaseBuilder(
            app,
            DogDatabase::class.java,
            "dog_database"
        ).build()

    @Provides
    fun provideDogDao(db: DogDatabase): DogDao = db.dogDao()

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun provideDogApi(retrofit: Retrofit): DogApi =
        retrofit.create(DogApi::class.java)

    @Provides
    @Singleton
    fun provideDogRepository(
        api: DogApi,
        dao: DogDao
    ): DogRepository = DogRepository(api, dao)

    @Provides
    @Singleton
    fun provideGetRandomDogUseCase(dogApi: DogApi): GetRandomDogUseCase {
        return GetRandomDogUseCase(dogApi)
    }

    @Provides
    fun provideGetBreedDogImagesUseCase(repository: DogRepository): GetBreedDogImagesUseCase {
        return GetBreedDogImagesUseCase(repository)
    }

    @Provides
    fun provideDogUseCase(repository: DogRepository): DogUseCase = DogUseCase(repository)
}