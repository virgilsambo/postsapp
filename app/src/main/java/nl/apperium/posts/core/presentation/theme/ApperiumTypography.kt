package nl.apperium.posts.core.presentation.theme

import androidx.compose.ui.text.TextStyle
import javax.annotation.concurrent.Immutable

@Immutable
data class ApperiumTypography(
    val bodyLarge: TextStyle = TextStyle.Default,
    val bodyMedium: TextStyle = TextStyle.Default,
    val bodySmall: TextStyle = TextStyle.Default,
    val titleLarge: TextStyle = TextStyle.Default,
    val labelSmall: TextStyle = TextStyle.Default,
)