package domain.usecases

import domain.BowlRepository
import domain.model.BowlDomainModel
import domain.util.DomainResult

class GetFoodItemUseCase(
    private val bowlRepository: BowlRepository,
) {
    suspend fun getFoodItem(foodID: Int): DomainResult<BowlDomainModel> {
        return bowlRepository.getFoodItem(foodID = foodID)
    }
}
