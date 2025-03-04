package nl.apperium.posts.feature.post.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nl.apperium.posts.core.domain.exception.NetworkException
import nl.apperium.posts.core.domain.utils.BannerManager
import nl.apperium.posts.core.domain.utils.ConnectionManager
import nl.apperium.posts.feature.post.domain.model.Post
import nl.apperium.posts.feature.post.domain.usecase.GetPostCommentsByPostId
import nl.apperium.posts.feature.post.presentation.uievent.PostDetailUIEvent
import nl.apperium.posts.feature.post.presentation.uistate.PostDetailScreenState
import javax.inject.Inject

@HiltViewModel
class PostDetailViewModel @Inject constructor(
    private val connectionManager: ConnectionManager,
    private val getPostCommentsByPostId: GetPostCommentsByPostId
) : ViewModel() {
    private val _postDetailScreenState = MutableStateFlow(PostDetailScreenState())
    val postDetailScreenState = _postDetailScreenState.asStateFlow()

    fun onEvent(event: PostDetailUIEvent) {
        when (event) {
            is PostDetailUIEvent.DeleteComment -> deleteComment(commentId = event.commentId)
            PostDetailUIEvent.RefreshComments -> refreshComments()
        }
    }

    fun loadScreenData(post: Post) {
        _postDetailScreenState.update {
            it.copy(post = post)
        }

        viewModelScope.launch {
            loadPostComments(postId = post.id)
        }
    }

    private fun setLoading(loading: Boolean = true) {
        _postDetailScreenState.update {
            it.copy(loading = loading)
        }
    }

    private suspend fun loadPostComments(postId: Int) {
        if (connectionManager.noInternet()) return

        setLoading(true)

        _postDetailScreenState.value.post?.let {
            val postCommentResult = getPostCommentsByPostId(postId)
            postCommentResult.fold(onSuccess = { postComments ->
                _postDetailScreenState.update {
                    it.copy(
                        postComments = postComments,
                    )
                }
            }, onFailure = { error ->
                when (error) {
                    is NetworkException -> {
                        BannerManager.showErrorBanner(error.text)
                    }
                }

                _postDetailScreenState.update {
                    it.copy(
                        postComments = emptyList()
                    )
                }
            })

            setLoading(false)
        }
    }

    private fun deleteComment(commentId: Int) {
        _postDetailScreenState.update {
            it.copy(
                postComments = it.postComments?.filter { comment -> comment.id != commentId }
            )
        }
    }

    private fun refreshComments() {
        _postDetailScreenState.value.post?.let { post ->
            viewModelScope.launch {
                loadPostComments(postId = post.id)
            }
        }
    }
}