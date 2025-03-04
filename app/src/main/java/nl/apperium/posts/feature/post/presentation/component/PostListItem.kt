package nl.apperium.posts.feature.post.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import nl.apperium.posts.core.presentation.theme.DefaultPreviews
import nl.apperium.posts.core.presentation.theme.DefaultProjectComposeTheme
import nl.apperium.posts.core.presentation.theme.ApperiumTheme
import nl.apperium.posts.feature.post.domain.model.Post

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostListItem(
    post: Post,
    modifier: Modifier = Modifier,
    onPostClicked: (post: Post) -> Unit = {}
) {
    Card(
        shape = ApperiumTheme.shapes.card,
        colors = CardDefaults.cardColors(
            containerColor = ApperiumTheme.colors.cardBackground,
            contentColor = Color.Unspecified,
            disabledContainerColor = Color.Unspecified,
            disabledContentColor = Color.Unspecified
        ),
        modifier = modifier
            .fillMaxWidth(),
        onClick = { onPostClicked(post) },


    ) {
        Column(
            modifier = Modifier.padding(ApperiumTheme.padding.small),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = post.title, style = ApperiumTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(ApperiumTheme.padding.medium))
            Text(text = post.body, style = ApperiumTheme.typography.bodyMedium)
        }
    }
}

@DefaultPreviews
@Composable
fun PostListItemPreview() {
    DefaultProjectComposeTheme {
        PostListItem(post = Post())
    }
}