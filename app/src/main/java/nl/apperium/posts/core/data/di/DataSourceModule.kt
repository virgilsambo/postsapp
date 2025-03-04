package nl.apperium.posts.core.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nl.apperium.posts.feature.post.data.datasource.PostRemoteDatasourceImpl
import nl.apperium.posts.feature.post.domain.datasource.PostRemoteDatasource


@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindPostRemoteDatasource(
        postRemoteDatasourceImpl: PostRemoteDatasourceImpl
    ): PostRemoteDatasource
}