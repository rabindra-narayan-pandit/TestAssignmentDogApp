package com.rabindra.testassignmentdogapp.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rabindra.testassignmentdogapp.data.remote.DogResponse
import com.rabindra.testassignmentdogapp.data.remote.DogResponseImage
import com.rabindra.testassignmentdogapp.domain.usecase.DogUseCase
import com.rabindra.testassignmentdogapp.domain.usecase.GetBreedDogImagesUseCase
import com.rabindra.testassignmentdogapp.domain.usecase.GetRandomDogUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
    @RunWith(JUnit4::class)
    class DogViewModelTest {

        @get:Rule
        val instantExecutorRule = InstantTaskExecutorRule()

        @MockK
        private lateinit var dogUseCase: DogUseCase

        @MockK
        private lateinit var getRandomDogUseCase: GetRandomDogUseCase

        @MockK
        private lateinit var getBreedDogImagesUseCase: GetBreedDogImagesUseCase

        private lateinit var dogViewModel: DogViewModel

        private val testDispatcher = UnconfinedTestDispatcher()

        @Before
        fun setUp() {
            MockKAnnotations.init(this)
            Dispatchers.setMain(testDispatcher)
            dogViewModel = DogViewModel(dogUseCase, getRandomDogUseCase, getBreedDogImagesUseCase)
        }

        @After
        fun tearDown() {
            Dispatchers.resetMain()
            unmockkAll()
        }

        @Test
        fun `fetchRandomDogImages fetches dog images successfully`() = runTest {
            val dogResponseImage = DogResponseImage(listOf("image1", "image2"), "success",)
            coEvery { getRandomDogUseCase() } returns dogResponseImage

            dogViewModel.fetchRandomDogImages()

            assertEquals(listOf("image1", "image2"), dogViewModel.dogImages.value)
            assertFalse(dogViewModel.loading.value)
        }

        @Test
        fun `searchBreedImages fetches breed dog images successfully`() = runTest {
            val breedResponse = DogResponse(listOf("breedImage1", "breedImage2"), "success")
            coEvery { getBreedDogImagesUseCase.execute("african") } returns breedResponse

            dogViewModel.searchBreedImages("african")

            assertEquals(listOf("breedImage1", "breedImage2"), dogViewModel.dogImagesByBreed.value)
        }

    }
