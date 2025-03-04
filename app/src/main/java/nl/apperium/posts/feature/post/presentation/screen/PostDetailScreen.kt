package nl.apperium.posts.feature.post.presentation.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ramcosta.composedestinations.annotation.Destination
import nl.apperium.posts.R
import nl.apperium.posts.core.presentation.navigation.PostNavGraph
import nl.apperium.posts.core.presentation.theme.DefaultPreviews
import nl.apperium.posts.core.presentation.theme.DefaultProjectComposeTheme
import nl.apperium.posts.core.presentation.theme.ApperiumTheme
import nl.apperium.posts.feature.post.domain.model.Post
import nl.apperium.posts.feature.post.domain.model.PostComment
import nl.apperium.posts.feature.post.presentation.component.PostCommentListItem
import nl.apperium.posts.feature.post.presentation.uievent.PostDetailUIEvent
import nl.apperium.posts.feature.post.presentation.viewmodel.PostDetailViewModel

@PostNavGraph
@Destination
@Composable
fun PostDetailScreen(
    post: Post,
    modifier: Modifier = Modifier,
    vm: PostDetailViewModel = hiltViewModel()
) {
    val state by vm.postDetailScreenState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        vm.loadScreenData(post = post)
    }

    PostDetailScreen(
        post = state.post,
        postComments = state.postComments ?: emptyList(),
        modifier = modifier,
        loading = state.loading,
        onEvent = { event -> vm.onEvent(event) }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PostDetailScreen(
    post: Post?,
    postComments: List<PostComment>,
    modifier: Modifier = Modifier,
    loading: Boolean = false,
    onEvent: (event: PostDetailUIEvent) -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ApperiumTheme.colors.background)
            .padding(ApperiumTheme.padding.large),
    ) {
        Text(text = post?.title ?: "", style = ApperiumTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(ApperiumTheme.dimensions.spacingMedium))
        Text(text = post?.body ?: "", style = ApperiumTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(ApperiumTheme.dimensions.spacingXLarge))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = stringResource(id = R.string.post_detail_header_comments),
                style = ApperiumTheme.typography.titleLarge,
                modifier = Modifier.weight(1f)
            )

            IconButton(onClick = { onEvent(PostDetailUIEvent.RefreshComments) }) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = stringResource(id = R.string.refresh_comments_icon)
                )
            }
        }

        Spacer(modifier = Modifier.height(ApperiumTheme.dimensions.spacingSmall))

        if (loading) {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Adaptive(200.dp),
                verticalItemSpacing = ApperiumTheme.dimensions.spacingXSmall,
                horizontalArrangement = Arrangement.spacedBy(ApperiumTheme.dimensions.spacingXSmall),
                content = {
                    items(postComments, key = { postComment -> postComment.id }) {
                        PostCommentListItem(
                            postComment = it,
                        ) { commentId ->
                            onEvent(PostDetailUIEvent.DeleteComment(commentId = commentId))
                        }
                    }
                })
        }
    }

}

@DefaultPreviews
@Composable
fun PostDetailScreenPreview() {
    DefaultProjectComposeTheme {
        PostDetailScreen(
            post = Post(),
            postComments = listOf(
                PostComment(id = 1),
                PostComment(id = 2),
                PostComment(id = 3)
            )
        )
    }
}