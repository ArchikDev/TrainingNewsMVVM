package com.dolgozitbudet.trainingnewsmvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import com.dolgozitbudet.trainingnewsmvvm.domain.usecases.AppEntryAppUseCases
import com.dolgozitbudet.trainingnewsmvvm.presentation.onboarding.OnBoardingScreen
import com.dolgozitbudet.trainingnewsmvvm.presentation.onboarding.OnBoardingViewModel
import com.dolgozitbudet.trainingnewsmvvm.ui.theme.TrainingNewsMVVMTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var useCases: AppEntryAppUseCases

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        installSplashScreen()

        lifecycleScope.launch {
            useCases.readAppEntry().collect {

            }
        }

        setContent {
            TrainingNewsMVVMTheme {
                Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {
                    val viewModel: OnBoardingViewModel = hiltViewModel()

                    OnBoardingScreen(
                        event = viewModel::onEvent
                    )
                }
            }
        }
    }
}