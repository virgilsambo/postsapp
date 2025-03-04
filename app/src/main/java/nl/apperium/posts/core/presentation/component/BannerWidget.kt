package nl.apperium.posts.core.presentation.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import nl.apperium.posts.core.domain.model.Banner
import nl.apperium.posts.core.presentation.theme.DefaultPreviews
import nl.apperium.posts.core.presentation.theme.DefaultProjectComposeTheme
import nl.apperium.posts.core.presentation.theme.ApperiumTheme

@Composable
fun BannerWidget(modifier: Modifier = Modifier, banner: Banner = Banner()) {
    Surface(
        modifier = modifier.padding(ApperiumTheme.padding.medium),
        color = banner.color,
        shape = ApperiumTheme.shapes.cornerSmall
    ) {
        Row(
            Modifier.padding(
                horizontal = ApperiumTheme.padding.medium,
                vertical = ApperiumTheme.padding.small
            )
        ) {
            Text(
                text = banner.message.asString(),
                color = banner.textColor,
                modifier = Modifier.fillMaxWidth(),
                style = ApperiumTheme.typography.bodyMedium
            )
        }
    }
}

@DefaultPreviews
@Composable
fun BannerPopUpPreview() {
    DefaultProjectComposeTheme {
        BannerWidget()
    }
}