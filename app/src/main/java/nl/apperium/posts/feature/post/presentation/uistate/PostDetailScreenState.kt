package nl.apperium.posts.feature.post.presentation.uistate

import nl.apperium.posts.feature.post.domain.model.Post
import nl.apperium.posts.feature.post.domain.model.PostComment

data class PostDetailScreenState(
    val post: Post? = null,
    val postComments: List<PostComment>? = null,
    val loading: Boolean = false
)
