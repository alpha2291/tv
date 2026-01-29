package com.example.bibletv.navigation


import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.bibletv.Design.AudioBible
import com.example.bibletv.Design.HomeScreen
import com.example.bibletv.Design.VerseReadingView
import com.example.bibletv.MainActivity
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow


@SuppressLint("SuspiciousIndentation")
@Composable
fun SetNavigation(mainActivity: MainActivity, mainViewModel: MainViewModel = hiltViewModel()) {

    val navController = rememberNavController()
    NavigationEffects(
        navigationChannel = mainViewModel.navigationChannel,
        navHostController = navController
    )

    NavHost(
        navController = navController,
        startDestination =
            DestinationClass.HomeScreen
    ) {
        composable(destination = DestinationClass.HomeScreen){
            HomeScreen()
        }
        composable(destination = DestinationClass.VerseReadingView){
            VerseReadingView()
        }
        composable(destination = DestinationClass.AudioBible){
            AudioBible()
        }

    }
}


@SuppressLint("ContextCastToActivity")
@Composable
fun NavigationEffects(
    navigationChannel: Channel<NavigationIntent>,
    navHostController: NavHostController
) {
    val activity = (LocalContext.current as? Activity)
    LaunchedEffect(activity, navHostController, navigationChannel)
    {
        navigationChannel.receiveAsFlow().collect { intent ->
            if (activity?.isFinishing == true) {
                return@collect
            }
            when(intent) {
                is NavigationIntent.NavigateBack -> {
                    if (intent.route != null) {
                        navHostController.popBackStack(intent.route, intent.inclusive)
                    } else {
                        navHostController.popBackStack()
                    }
                }
                is NavigationIntent.NavigateTo -> {
                    navHostController.navigate(intent.route) {
                        launchSingleTop = intent.isSingleTop

                        intent.popUpToRoute?.let { popUpToRoute ->
                            popUpTo(popUpToRoute) { inclusive = intent.inclusive }
                        }
                    }
                }
                else -> {}
            }
        }
    }
}


