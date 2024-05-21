package domain.usecases

import domain.BowlRepository
import domain.model.BowlDomainModel
import domain.util.DomainResult
import kotlinx.coroutines.flow.Flow

class GetAllFavoriteFoodItemsUseCase(
    private val bowlRepository: BowlRepository,
) {
    suspend fun getFavoriteFoodItems(): Flow<DomainResult<List<BowlDomainModel>>> {
        return bowlRepository.getFavoriteFoodItems()
    }
}
