package nl.apperium.posts.core.presentation.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.ui.graphics.Shape
import javax.annotation.concurrent.Immutable

@Immutable
data class ApperiumShapes(
    val button: Shape = RoundedCornerShape(ZeroCornerSize),
    val card: Shape = RoundedCornerShape(ZeroCornerSize),
    val cornerSmall: Shape = RoundedCornerShape(ZeroCornerSize)
)