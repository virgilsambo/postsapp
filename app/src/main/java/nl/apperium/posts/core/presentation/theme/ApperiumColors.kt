package nl.apperium.posts.core.presentation.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class ApperiumColors(
    val primary: Color = Color.Unspecified,
    val secondary: Color = Color.Unspecified,
    val tertiary: Color = Color.Unspecified,
    val background: Color = Color.Unspecified,
    val cardBackground: Color = Color.Unspecified
)