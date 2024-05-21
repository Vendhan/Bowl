package data.database.entity

import androidx.room.TypeConverter

class Converter {
    @TypeConverter
    fun fromString(value: String): List<String> {
        return value.split("*")
    }

    @TypeConverter
    fun fromList(list: List<String>): String {
        return list.joinToString("*")
    }
}
