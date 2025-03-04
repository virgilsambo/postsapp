package nl.apperium.posts.core.domain.model

import androidx.compose.ui.graphics.Color
import nl.apperium.posts.R

data class Banner(
    val color: Color = Color.Cyan,
    val textColor: Color = Color.White,
    val message: UiText = UiText.StringResource(R.string.something_went_wrong),
)
