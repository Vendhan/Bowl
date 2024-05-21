package domain.usecases

import domain.BowlRepository

class UpdateFavoriteUseCase(
    private val bowlRepository: BowlRepository,
) {
    suspend fun updateFavorite(
        foodID: Int,
        isFavorite: Boolean,
    ) {
        bowlRepository.updateFavorite(id = foodID, favorite = isFavorite)
    }
}
