package nl.apperium.posts.feature.post.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import nl.apperium.posts.R
import nl.apperium.posts.core.presentation.navigation.PostNavGraph
import nl.apperium.posts.core.presentation.theme.DefaultPreviews
import nl.apperium.posts.core.presentation.theme.DefaultProjectComposeTheme
import nl.apperium.posts.core.presentation.theme.ApperiumTheme
import nl.apperium.posts.destinations.PostDetailScreenDestination
import nl.apperium.posts.feature.post.domain.model.Post
import nl.apperium.posts.feature.post.presentation.component.PostListItem
import nl.apperium.posts.feature.post.presentation.viewmodel.PostViewModel

@PostNavGraph(start = true)
@Destination
@Composable
fun PostScreen(
    navigator: DestinationsNavigator,
    modifier: Modifier = Modifier,
    vm: PostViewModel = hiltViewModel()
) {
    val state by vm.postScreenState.collectAsStateWithLifecycle()

    fun loadPosts() {
        vm.loadPosts()
    }

    fun onPostClicked(post: Post) {
        navigator.navigate(PostDetailScreenDestination(post = post))
    }

    PostScreen(
        posts = state.posts ?: emptyList(),
        modifier = modifier,
        loading = state.loading,
        loadPosts = ::loadPosts,
        onPostClicked = ::onPostClicked
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PostScreen(
    posts: List<Post>,
    modifier: Modifier = Modifier,
    loading: Boolean = false,
    loadPosts: () -> Unit = {},
    onPostClicked: (post: Post) -> Unit = {}
) {
    val pullRefreshState = rememberPullRefreshState(loading, { loadPosts() })

    Column(modifier = modifier.fillMaxSize().background(ApperiumTheme.colors.background)) {
        Spacer(modifier = Modifier.height(ApperiumTheme.padding.large))
        Text(
            text = stringResource(id = R.string.post_screen_title),
            style = ApperiumTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = ApperiumTheme.padding.large)
        )

        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(ApperiumTheme.padding.large)
                .pullRefresh(pullRefreshState),
            contentAlignment = Alignment.Center
        ) {
            if (posts.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()), contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier
                            .align(Alignment.Center),
                        textAlign = TextAlign.Center,
                        text = stringResource(id = R.string.no_posts_found)
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(ApperiumTheme.padding.medium)
                ) {
                    items(posts, key = { post -> post.id }) {
                        PostListItem(
                            post = it,
                            onPostClicked = onPostClicked
                        )
                    }
                }
            }

            PullRefreshIndicator(
                refreshing = loading,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
    }
}

@DefaultPreviews
@Composable
fun PostScreenPreview() {
    DefaultProjectComposeTheme {
        PostScreen(posts = listOf(Post(id = 1), Post(id = 2), Post(id = 3)))
    }
}