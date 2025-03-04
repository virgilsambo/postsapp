package nl.apperium.posts.core.presentation.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp


// region Local providers
val LocalApperiumColors = staticCompositionLocalOf {
    ApperiumColors()
}
val LocalApperiumTypography = staticCompositionLocalOf {
    ApperiumTypography()
}

val LocalApperiumShapes = staticCompositionLocalOf {
    ApperiumShapes()
}

val LocalApperiumPadding = staticCompositionLocalOf {
    ApperiumPadding()
}

val LocalApperiumDimensions = staticCompositionLocalOf {
    ApperiumDimensions()
}

val LocalApperiumSizes = staticCompositionLocalOf {
    ApperiumSizes()
}
// endregion

// region Themes
object ApperiumTheme {
    val typography: ApperiumTypography
        @Composable
        get() = LocalApperiumTypography.current
    val shapes: ApperiumShapes
        @Composable
        get() = LocalApperiumShapes.current
    val padding: ApperiumPadding
        @Composable
        get() = LocalApperiumPadding.current
    val dimensions: ApperiumDimensions
        @Composable
        get() = LocalApperiumDimensions.current
    val sizes: ApperiumSizes
        @Composable
        get() = LocalApperiumSizes.current
    val colors: ApperiumColors
        @Composable
        get() = LocalApperiumColors.current
}

@Composable
fun LightTheme(content: @Composable () -> Unit) {
    val apperiumColors = ApperiumColors(
        primary = Purple40,
        secondary = PurpleGray40,
        tertiary = Pink40,
        background = White,
        cardBackground = Purple80,
    )

    val apperiumTypography = ApperiumTypography(
        bodyLarge = bodyLarge,
        bodyMedium = bodyMedium,
        bodySmall = bodySmall,
        titleLarge = titleLarge,
        labelSmall = labelSmall,
    )

    val apperiumShapes = ApperiumShapes(
        button = RoundedCornerShape(8.dp),
        card = RoundedCornerShape(16.dp),
        cornerSmall = RoundedCornerShape(8.dp)
    )

    val apperiumPadding = ApperiumPadding(
        small = 8.dp,
        medium = 16.dp,
        large = 24.dp
    )

    val apperiumDimensions = ApperiumDimensions(
        spacingXSmall = 4.dp,
        spacingSmall = 8.dp,
        spacingMedium = 16.dp,
        spacingLarge = 24.dp,
        spacingXLarge = 32.dp,
    )

    val apperiumSizes = ApperiumSizes(
        dialog = 100.dp
    )

    CompositionLocalProvider(
        LocalApperiumColors provides apperiumColors,
        LocalApperiumTypography provides apperiumTypography,
        LocalApperiumShapes provides apperiumShapes,
        LocalApperiumPadding provides apperiumPadding,
        LocalApperiumDimensions provides apperiumDimensions,
        LocalApperiumSizes provides apperiumSizes
    ) {
        MaterialTheme(content = content)
    }
}

@Composable
fun DarkTheme(content: @Composable () -> Unit) {
    val apperiumColors = ApperiumColors(
        primary = Purple80,
        secondary = PurpleGray80,
        tertiary = Pink80,
        background = Dark,
        cardBackground = Gray
    )

    val apperiumTypography = ApperiumTypography(
        bodyLarge = bodyLarge.copy(color = White),
        bodyMedium = bodyMedium.copy(color = White),
        bodySmall = bodySmall.copy(color = White),
        titleLarge = titleLarge.copy(color = White),
        labelSmall = labelSmall.copy(color = White),
    )

    val apperiumShapes = ApperiumShapes(
        button = RoundedCornerShape(8.dp),
        card = RoundedCornerShape(16.dp),
        cornerSmall = RoundedCornerShape(8.dp)
    )

    val apperiumPadding = ApperiumPadding(
        small = 8.dp,
        medium = 16.dp,
        large = 24.dp
    )

    val apperiumDimensions = ApperiumDimensions(
        spacingXSmall = 4.dp,
        spacingSmall = 8.dp,
        spacingMedium = 16.dp,
        spacingLarge = 24.dp,
        spacingXLarge = 32.dp,
    )

    val apperiumSizes = ApperiumSizes(
        dialog = 100.dp
    )

    CompositionLocalProvider(
        LocalApperiumColors provides apperiumColors,
        LocalApperiumTypography provides apperiumTypography,
        LocalApperiumShapes provides apperiumShapes,
        LocalApperiumPadding provides apperiumPadding,
        LocalApperiumDimensions provides apperiumDimensions,
        LocalApperiumSizes provides apperiumSizes
    ) {
        MaterialTheme(content = content)
    }
}
// endregion

@Composable
fun DefaultProjectComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            if (darkTheme) {
                DarkTheme(content = content)
            } else {
                LightTheme(content = content)
            }
        }

        darkTheme -> DarkTheme(content = content)
        else -> LightTheme(content = content)
    }
}