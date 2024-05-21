package domain.usecases

import domain.BowlRepository
import domain.model.BowlDomainModel
import domain.util.DomainResult
import kotlinx.coroutines.flow.Flow

class GetAllFoodItemsUseCase(
    private val bowlRepository: BowlRepository,
) {
    suspend fun getAllFoodItems(): Flow<DomainResult<List<BowlDomainModel>>> {
        return bowlRepository.getFoodItems()
    }
}
