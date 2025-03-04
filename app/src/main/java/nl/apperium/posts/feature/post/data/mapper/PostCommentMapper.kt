package nl.apperium.posts.feature.post.data.mapper

import nl.apperium.posts.feature.post.data.network.response.PostCommentResponse
import nl.apperium.posts.feature.post.domain.model.PostComment

fun List<PostCommentResponse>.toPostComments(): List<PostComment> = map { it.toPostComment() }

fun PostCommentResponse.toPostComment(): PostComment =
    PostComment(postId = postId, id = id, name = name, email = email, body = body)