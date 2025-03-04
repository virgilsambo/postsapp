package nl.apperium.posts.feature.post.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nl.apperium.posts.core.domain.utils.BannerManager
import nl.apperium.posts.core.domain.utils.ConnectionManager
import nl.apperium.posts.feature.post.domain.usecase.GetPostsUseCase
import nl.apperium.posts.feature.post.presentation.uistate.PostScreenState
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val connectionManager: ConnectionManager,
    private val getPostsUseCase: GetPostsUseCase
) : ViewModel() {
    private val _postScreenState = MutableStateFlow(PostScreenState())
    val postScreenState = _postScreenState.asStateFlow()

    init {
        loadPosts()
    }

    fun loadPosts() {
        _postScreenState.update {
            it.copy(loading = true)
        }

        viewModelScope.launch {
            if (!connectionManager.noInternet()) {
                val postsResult = getPostsUseCase()
                postsResult.fold(onSuccess = { posts ->
                    _postScreenState.update {
                        it.copy(
                            posts = posts,
                            loading = false
                        )
                    }
                }, onFailure = {
                    BannerManager.showSomethingWentWrongBanner()

                    _postScreenState.update {
                        it.copy(
                            posts = emptyList(),
                            loading = false
                        )
                    }
                })
            } else {
                _postScreenState.update {
                    it.copy(loading = false, posts = emptyList())
                }
            }
        }
    }
}