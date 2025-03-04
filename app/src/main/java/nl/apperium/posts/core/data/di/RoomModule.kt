package nl.apperium.posts.core.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nl.apperium.posts.core.data.database.ApperiumDatabase
import nl.apperium.posts.feature.post.data.room.dao.UserDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    /**
     * Returns the app database.
     */
    @Singleton
    @Provides
    fun getApperiumDatabase(context: Context): ApperiumDatabase = ApperiumDatabase.invoke(context)

    /**
     * Returns UserDao implementation
     */
    @Singleton
    @Provides
    fun getUserDao(database: ApperiumDatabase): UserDao = database.userDao()
}