package nl.apperium.posts.core.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import nl.apperium.posts.feature.post.data.room.dao.UserDao
import nl.apperium.posts.feature.post.data.room.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ApperiumDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        // singleton for AppDatabase. Create new database if it doesn't exist yet
        @Volatile
        private var instance: ApperiumDatabase? = null
        private val LOCK = Any()

        /**
         * checks if app database exists yet in the current session. call this to init the database
         */
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        /**
         * builds the app database
         */
        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            ApperiumDatabase::class.java, "default_name_database.db"
        ).build()
    }
}