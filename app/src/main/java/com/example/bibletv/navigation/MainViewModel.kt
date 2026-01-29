package com.example.bibletv.navigation


import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(appNavigator: AppNavigator) : ViewModel() {
    val navigationChannel = appNavigator.navigationChannel
}


