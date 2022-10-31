package com.example.viewmodelexample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

// lifecycle-viewmodel-ktx:2.4.1 (version number is not the latest one)
// I have troubles in class when using the latest version
// This class file can be put into MainViewModel too

//val is necessary
class MainViewModelFactory(private val lastValue: Int): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(lastValue) as T
        }
}