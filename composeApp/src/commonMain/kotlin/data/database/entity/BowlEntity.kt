package data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BowlEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String,
    val history: String,
    val origin: String,
    val cookingInstructions: List<String>,
    val isFavorite: Boolean = false,
)
