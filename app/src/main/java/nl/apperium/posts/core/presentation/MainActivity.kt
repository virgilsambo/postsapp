package nl.apperium.posts.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import dagger.hilt.android.AndroidEntryPoint
import nl.apperium.posts.NavGraphs
import nl.apperium.posts.core.domain.utils.BannerManager
import nl.apperium.posts.core.presentation.component.BannerWidget
import nl.apperium.posts.core.presentation.modifier.noRippleClickable
import nl.apperium.posts.core.presentation.theme.DefaultProjectComposeTheme
import nl.apperium.posts.core.presentation.theme.ApperiumTheme
import nl.apperium.posts.core.presentation.viewmodel.MainViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            this.setKeepOnScreenCondition {
                mainViewModel.showSplashScreen
            }
        }

        setContent {
            val state = BannerManager.bannerState.collectAsStateWithLifecycle().value

            DefaultProjectComposeTheme {
                val navController = rememberNavController()

                SystemBar()

                Surface(
                    modifier = Modifier.fillMaxSize(), color = ApperiumTheme.colors.background
                ) {
                    // Scaffold is being used to prevent the screen from overlapping the System Bar
                    Scaffold { innerPadding ->
                        DestinationsNavHost(
                            modifier = Modifier.padding(innerPadding),
                            navController = navController,
                            navGraph = NavGraphs.root,
                            engine = rememberAnimatedNavHostEngine()
                        )

                        AnimatedVisibility(
                            visible = state.showBanner,
                            enter = slideInVertically(),
                            exit = fadeOut() + slideOutVertically(),
                        ) {
                            BannerWidget(
                                banner = state.banner,
                                modifier = Modifier.noRippleClickable {
                                    BannerManager.hideBanner()
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun SystemBar(
        systemColor: Color = ApperiumTheme.colors.background
    ) {
        val barColor = systemColor.toArgb()
        val darkTheme = isSystemInDarkTheme()

        LaunchedEffect(darkTheme) {
            val statusBarStyle: SystemBarStyle
            val navigationBarStyle: SystemBarStyle

            if (darkTheme) {
                statusBarStyle = SystemBarStyle.dark(barColor)
                navigationBarStyle = SystemBarStyle.dark(barColor)
            } else {
                statusBarStyle = SystemBarStyle.light(barColor, barColor)
                navigationBarStyle = SystemBarStyle.light(barColor, barColor)
            }

            enableEdgeToEdge(
                statusBarStyle = statusBarStyle,
                navigationBarStyle = navigationBarStyle,
            )
        }
    }
}