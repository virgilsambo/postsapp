package nl.apperium.posts.core.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    companion object {
        const val SPLASH_DELAY = 2500L
    }

    var showSplashScreen = true
        private set

    init {
        viewModelScope.launch {
            delay(SPLASH_DELAY)
            showSplashScreen = false
        }
    }
}