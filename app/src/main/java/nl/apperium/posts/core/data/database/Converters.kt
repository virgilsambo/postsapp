package nl.apperium.posts.core.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import nl.apperium.posts.core.domain.model.Example
import java.util.Collections

class Converters {
    private val gson = Gson()

    // Example converter
    @TypeConverter
    fun toExample(value: String?): Example? =
        if (value == null) null else gson.fromJson(value, Example::class.java)

    @TypeConverter
    fun exampleToString(value: Example?): String? = if (value == null) null else gson.toJson(value)

    // Example List converter
    @TypeConverter
    fun toExampleList(value: String?): MutableList<Example>? {
        if (value == null) return Collections.emptyList()
        val listType = object : TypeToken<MutableList<Example>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun exampleListToString(value: MutableList<Example>?): String? {
        return gson.toJson(value)
    }
}