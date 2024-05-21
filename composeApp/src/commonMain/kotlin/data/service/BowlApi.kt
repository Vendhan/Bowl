package data.service

import data.service.model.BowlResponseModelWrapper
import domain.util.DomainResult

interface BowlApi {
    suspend fun fetchFoodItems(): DomainResult<BowlResponseModelWrapper>
}
