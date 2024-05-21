package di

import data.BowlRepositoryImpl
import data.mapper.BowlMapper
import domain.BowlRepository
import domain.usecases.GetAllFavoriteFoodItemsUseCase
import domain.usecases.GetAllFoodItemsUseCase
import domain.usecases.GetFoodItemUseCase
import domain.usecases.UpdateFavoriteUseCase
import org.koin.dsl.module

val appModule =
    module {

        single<BowlMapper> {
            BowlMapper()
        }
        single<BowlRepository> {
            BowlRepositoryImpl(get(), get(), get())
        }

        single<GetAllFoodItemsUseCase> {
            GetAllFoodItemsUseCase(get())
        }

        single<GetFoodItemUseCase> {
            GetFoodItemUseCase(get())
        }

        single<UpdateFavoriteUseCase> {
            UpdateFavoriteUseCase(get())
        }

        single<GetAllFavoriteFoodItemsUseCase> {
            GetAllFavoriteFoodItemsUseCase(get())
        }
    }
