package presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.model.BowlDomainModel
import domain.usecases.GetFoodItemUseCase
import domain.usecases.UpdateFavoriteUseCase
import domain.util.DomainResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val getFoodItemUseCase: GetFoodItemUseCase,
    private val updateFavoriteUseCase: UpdateFavoriteUseCase,
) : ViewModel() {
    private val _foodItem = MutableStateFlow<DomainResult<BowlDomainModel>>(DomainResult.Loading)
    val foodItem: StateFlow<DomainResult<BowlDomainModel>>
        get() = _foodItem

    fun getFoodItem(foodID: Int) {
        viewModelScope.launch {
            _foodItem.value =
                getFoodItemUseCase
                    .getFoodItem(foodID = foodID)
        }
    }

    fun updateFavorite(
        foodID: Int,
        isFavorite: Boolean,
    ) {
        viewModelScope.launch {
            updateFavoriteUseCase
                .updateFavorite(foodID = foodID, isFavorite = isFavorite)
        }
    }
}
