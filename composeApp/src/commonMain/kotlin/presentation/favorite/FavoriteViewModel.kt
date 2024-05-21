package presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.model.BowlDomainModel
import domain.usecases.GetAllFavoriteFoodItemsUseCase
import domain.util.DomainResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val getAllFavoriteFoodItemsUseCase: GetAllFavoriteFoodItemsUseCase,
) : ViewModel() {
    private val _favoriteFoodItems = MutableStateFlow<DomainResult<List<BowlDomainModel>>>(DomainResult.Loading)
    val favoriteFoodItems: StateFlow<DomainResult<List<BowlDomainModel>>>
        get() = _favoriteFoodItems

    fun getFavoriteFoodItems() {
        viewModelScope.launch {
            getAllFavoriteFoodItemsUseCase
                .getFavoriteFoodItems()
                .collect {
                    _favoriteFoodItems.value = it
                }
        }
    }
}
