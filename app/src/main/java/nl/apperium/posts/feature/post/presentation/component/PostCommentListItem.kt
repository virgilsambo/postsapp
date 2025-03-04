package nl.apperium.posts.feature.post.presentation.component

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import nl.apperium.posts.R
import nl.apperium.posts.core.presentation.theme.Dark
import nl.apperium.posts.core.presentation.theme.DefaultPreviews
import nl.apperium.posts.core.presentation.theme.DefaultProjectComposeTheme
import nl.apperium.posts.core.presentation.theme.ApperiumTheme
import nl.apperium.posts.core.presentation.theme.Purple80
import nl.apperium.posts.feature.post.domain.model.PostComment

@Composable
fun PostCommentListItem(
    postComment: PostComment,
    modifier: Modifier = Modifier,
    onDeleteClicked: (commentId: Int) -> Unit = {}
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
    ) {
        Column(
            modifier = Modifier.padding(ApperiumTheme.padding.small),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = postComment.name,
                    style = ApperiumTheme.typography.titleLarge,
                    modifier = Modifier.weight(1f)
                )
                IconButton(onClick = { onDeleteClicked(postComment.id) }) {
                    Icon(
                        tint = if (isSystemInDarkTheme()) Purple80 else Dark,
                        imageVector = Icons.Default.Delete,
                        contentDescription = stringResource(id = R.string.refresh_comments_icon)
                    )
                }
            }
            Text(text = postComment.email, style = ApperiumTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(ApperiumTheme.padding.medium))
            Text(text = postComment.body, style = ApperiumTheme.typography.bodyMedium)
        }
    }
}

@DefaultPreviews
@Composable
fun PostCommentListItemPreview() {
    DefaultProjectComposeTheme {
        PostCommentListItem(postComment = PostComment())
    }
}