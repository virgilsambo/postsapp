package nl.apperium.posts.feature.post.presentation.uistate

import nl.apperium.posts.feature.post.domain.model.Post

data class PostScreenState(val posts: List<Post>? = null, val loading: Boolean = true)
