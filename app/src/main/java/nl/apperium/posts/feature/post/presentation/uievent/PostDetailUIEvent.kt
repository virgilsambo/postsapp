package nl.apperium.posts.feature.post.presentation.uievent

sealed class PostDetailUIEvent {
    data class DeleteComment(val commentId: Int) : PostDetailUIEvent()

    object RefreshComments : PostDetailUIEvent()
}
