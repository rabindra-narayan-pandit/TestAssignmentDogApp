package com.rabindra.testassignmentdogapp.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rabindra.testassignmentdogapp.data.local.DogEntity
import com.rabindra.testassignmentdogapp.domain.usecase.DogUseCase
import com.rabindra.testassignmentdogapp.domain.usecase.GetBreedDogImagesUseCase
import com.rabindra.testassignmentdogapp.domain.usecase.GetRandomDogUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogViewModel @Inject constructor(
    private val useCase: DogUseCase,
    private val getRandomDogUseCase: GetRandomDogUseCase,
    private val getBreedDogImagesUseCase:GetBreedDogImagesUseCase
) : ViewModel() {

    private val _dogImages = MutableStateFlow<List<String>>(emptyList())
    val dogImages: StateFlow<List<String>> = _dogImages

    private val _dogImagesByBreed = mutableStateOf<List<String>>(listOf())
    val dogImagesByBreed: State<List<String>> get() = _dogImagesByBreed

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading


    private val _dogs = MutableStateFlow<List<DogEntity>>(emptyList())
    val dogs: StateFlow<List<DogEntity>> get() = _dogs

    init {
        fetchRandomDogImages()
    }

     fun fetchRandomDogImages() {
        viewModelScope.launch {
            _loading.value = true
            try {
                val response = getRandomDogUseCase()
                if (response.status == "success") {
                    _dogImages.value = response.message
                }
            } catch (e: Exception) {
                // Handle exception (e.g., log the error, show a message)
                _dogImages.value = emptyList()
            } finally {
                _loading.value = false
            }
        }
    }
    fun searchBreedImages(breed: String) {
        viewModelScope.launch {
            try {
                val response = getBreedDogImagesUseCase.execute(breed.lowercase())
                _dogImagesByBreed.value = response.message
            } catch (e: Exception) {
                // Handle the error
                _dogImagesByBreed.value = listOf()
            }
        }
    }

}