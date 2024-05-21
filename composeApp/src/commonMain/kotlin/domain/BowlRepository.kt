package domain

import domain.model.BowlDomainModel
import domain.util.DomainResult
import kotlinx.coroutines.flow.Flow

interface BowlRepository {
    suspend fun getFoodItems(): Flow<DomainResult<List<BowlDomainModel>>>

    suspend fun getFoodItem(foodID: Int): DomainResult<BowlDomainModel>

    suspend fun getFavoriteFoodItems(): Flow<DomainResult<List<BowlDomainModel>>>

    suspend fun updateFavorite(
        id: Int,
        favorite: Boolean,
    )
}
