package nl.apperium.posts.core.presentation.modal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import nl.apperium.posts.core.presentation.theme.DefaultPreviews
import nl.apperium.posts.core.presentation.theme.DefaultProjectComposeTheme
import nl.apperium.posts.core.presentation.theme.ApperiumTheme


@Composable
fun LoadingIndicator(
    onDismissRequest: () -> Unit = {}
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(ApperiumTheme.sizes.dialog)
                .background(color = White, shape = ApperiumTheme.shapes.cornerSmall)
        ) {
            CircularProgressIndicator()
        }
    }
}

@DefaultPreviews
@Composable
fun LoadingIndicatorPreview() {
    DefaultProjectComposeTheme {
        LoadingIndicator()
    }
}