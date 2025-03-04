package nl.apperium.posts.feature.post.data.mapper

import nl.apperium.posts.feature.post.data.network.response.PostResponse
import nl.apperium.posts.feature.post.domain.model.Post

fun List<PostResponse>.toPosts(): List<Post> = map { it.toPost() }

fun PostResponse.toPost(): Post = Post(userId = userId, id = id, title = title, body = body)