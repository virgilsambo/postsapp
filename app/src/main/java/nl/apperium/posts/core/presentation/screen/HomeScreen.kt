package nl.apperium.posts.core.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import nl.apperium.posts.R
import nl.apperium.posts.core.presentation.modifier.noRippleClickable
import nl.apperium.posts.core.presentation.theme.DefaultPreviews
import nl.apperium.posts.core.presentation.theme.DefaultProjectComposeTheme
import nl.apperium.posts.core.presentation.theme.ApperiumTheme
import nl.apperium.posts.destinations.PostScreenDestination

@RootNavGraph(start = true)
@Destination
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator,
    modifier: Modifier = Modifier
) {
    fun onTextClicked() {
        navigator.navigate(PostScreenDestination())
    }

    HomeScreen(modifier = modifier, onTextClicked = ::onTextClicked)
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier, onTextClicked: () -> Unit = {}) {
    Box(modifier = modifier.fillMaxSize().background(color = ApperiumTheme.colors.background)) {
        Text(
            text = stringResource(id = R.string.home_screen_text),
            style = ApperiumTheme.typography.titleLarge,
            modifier = Modifier
                .align(Alignment.Center)
                .noRippleClickable { onTextClicked() })
    }
}

@DefaultPreviews
@Composable
fun HomeScreenPreview() {
    DefaultProjectComposeTheme {
        HomeScreen()
    }
}

