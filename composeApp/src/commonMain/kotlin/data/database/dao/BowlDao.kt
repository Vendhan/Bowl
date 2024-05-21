package data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import data.database.entity.BowlEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BowlDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFoodItems(foodItems: List<BowlEntity>)

    @Query("SELECT * FROM BowlEntity")
    fun getFoodItems(): Flow<List<BowlEntity>>

    @Query("SELECT * FROM BowlEntity WHERE id = :foodID")
    suspend fun getFoodItem(foodID: Int): BowlEntity

    @Query("SELECT * FROM BowlEntity")
    suspend fun checkFoodItems(): List<BowlEntity>

    @Query("SELECT * FROM BowlEntity WHERE isFavorite = 1")
    fun getFavoriteFoodItems(): Flow<List<BowlEntity>>

    @Query("UPDATE BowlEntity SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun updateFavorite(
        id: Int,
        isFavorite: Boolean,
    )
}
