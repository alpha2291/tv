package com.example.bibletv.navigation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class NavViewModel @Inject constructor(val appNavigator: AppNavigator) : ViewModel() {

    fun onBackStack(){
        appNavigator.tryNavigateBack()
    }
    fun onNavigateToHomeScreen(){
        appNavigator.tryNavigateTo(DestinationClass.HomeScreen.fullRoute)
    }
    fun onNavigateToVerseReadingView(){
        appNavigator.tryNavigateTo(DestinationClass.VerseReadingView.fullRoute)
    }
    fun onNavigateToAudioBible(){
        appNavigator.tryNavigateTo(DestinationClass.AudioBible.fullRoute)
    }
}

