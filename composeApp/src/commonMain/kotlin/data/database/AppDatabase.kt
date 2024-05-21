package data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import data.database.dao.BowlDao
import data.database.entity.BowlEntity
import data.database.entity.Converter

@Database(entities = [BowlEntity::class], version = 1)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getBowlDao(): BowlDao
}
