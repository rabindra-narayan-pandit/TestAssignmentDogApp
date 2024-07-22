package com.rabindra.testassignmentdogapp.presentation.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.rabindra.testassignmentdogapp.presentation.viewmodel.DogViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollectionsScreen(
    viewModel: DogViewModel = hiltViewModel()
) {
    val dogs by viewModel.dogs.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Dog Collections") })
        },
        content = {
            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                dogs.forEach { dog ->
                    Row(modifier = Modifier.padding(8.dp)) {
                        Image(
                            painter = rememberAsyncImagePainter(dog.imageUrl),
                            contentDescription = "Dog Image",
                            modifier = Modifier
                                .size(100.dp)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text("Dog ID: ${dog.id}")
                    }
                }
            }
        }
    )
}