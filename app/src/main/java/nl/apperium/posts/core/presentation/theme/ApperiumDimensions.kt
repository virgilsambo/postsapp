package nl.apperium.posts.core.presentation.theme

import androidx.compose.ui.unit.Dp
import javax.annotation.concurrent.Immutable

@Immutable
data class ApperiumDimensions(
    val spacingXSmall: Dp = Dp.Unspecified,
    val spacingSmall: Dp = Dp.Unspecified,
    val spacingMedium: Dp = Dp.Unspecified,
    val spacingLarge: Dp = Dp.Unspecified,
    val spacingXLarge: Dp = Dp.Unspecified,
)