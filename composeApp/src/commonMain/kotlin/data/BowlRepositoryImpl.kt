package data

import data.database.dao.BowlDao
import data.mapper.BowlMapper
import data.service.BowlApi
import data.service.model.BowlResponseModelWrapper
import domain.BowlRepository
import domain.model.BowlDomainModel
import domain.util.DomainResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BowlRepositoryImpl(
    private val bowlDao: BowlDao,
    private val bowlApi: BowlApi,
    private val bowlMapper: BowlMapper,
) : BowlRepository {
    override suspend fun getFoodItems(): Flow<DomainResult<List<BowlDomainModel>>> {
        if (!isCached()) {
            when (val result = getFoodItemsFromApi()) {
                is DomainResult.Success -> {
                    val foodItems =
                        result.data.foodItems.map {
                            val data = bowlMapper.mapToDataEntity(it)
                            bowlMapper.mapFromEntityToDomainModel(data)
                        }
                    return flow {
                        emit(DomainResult.Success(foodItems))
                    }
                }
                else -> return flow {
                    emit(DomainResult.Error("error"))
                }
            }
        } else {
            return flow {
                try {
                    bowlDao.getFoodItems().collect {
                        emit(
                            DomainResult.Success(
                                it.map {
                                    bowlMapper.mapFromEntityToDomainModel(it)
                                },
                            ),
                        )
                    }
                } catch (e: Exception) {
                    emit(DomainResult.Error(e.message.toString()))
                }
            }
        }
    }

    override suspend fun getFoodItem(foodID: Int): DomainResult<BowlDomainModel> {
        return try {
            DomainResult.Success(
                bowlMapper.mapFromEntityToDomainModel(bowlDao.getFoodItem(foodID = foodID)),
            )
        } catch (e: Exception) {
            DomainResult.Error(e.message.toString())
        }
    }

    private suspend fun getFoodItemsFromApi(): DomainResult<BowlResponseModelWrapper> {
        val foodItems = bowlApi.fetchFoodItems()
        when (foodItems) {
            is DomainResult.Success -> {
                val foodItemsEntity =
                    foodItems.data.foodItems.map {
                        bowlMapper.mapToDataEntity(it)
                    }
                bowlDao.insertFoodItems(foodItemsEntity)
            }
            else -> {}
        }
        return foodItems
    }

    override suspend fun getFavoriteFoodItems(): Flow<DomainResult<List<BowlDomainModel>>> =
        flow {
            try {
                bowlDao.getFavoriteFoodItems().collect {
                    emit(
                        DomainResult.Success(
                            it.map {
                                bowlMapper.mapFromEntityToDomainModel(it)
                            },
                        ),
                    )
                }
            } catch (e: Exception) {
                emit(DomainResult.Error(e.message.toString()))
            }
        }

    override suspend fun updateFavorite(
        id: Int,
        favorite: Boolean,
    ) {
        bowlDao.updateFavorite(
            id = id,
            isFavorite = favorite,
        )
    }

    private suspend fun isCached(): Boolean {
        return bowlDao.checkFoodItems().isNotEmpty()
    }
}
