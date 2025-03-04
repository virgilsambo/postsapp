package nl.apperium.posts.core.presentation.theme

import androidx.compose.ui.unit.Dp
import javax.annotation.concurrent.Immutable

@Immutable
data class ApperiumPadding(
    val small: Dp = Dp.Unspecified,
    val medium: Dp = Dp.Unspecified,
    val large: Dp = Dp.Unspecified
)