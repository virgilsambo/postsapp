package nl.apperium.posts.core.presentation.navigation

import com.ramcosta.composedestinations.annotation.NavGraph
import com.ramcosta.composedestinations.annotation.RootNavGraph


@RootNavGraph
@NavGraph
annotation class PostNavGraph(
    val start: Boolean = false
)