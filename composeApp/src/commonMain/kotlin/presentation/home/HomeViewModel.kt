package presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.model.BowlDomainModel
import domain.usecases.GetAllFoodItemsUseCase
import domain.util.DomainResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getAllFoodItemsUseCase: GetAllFoodItemsUseCase,
) : ViewModel() {
    init {
        getAllFoodItems()
    }

    private val _foodItems = MutableStateFlow<DomainResult<List<BowlDomainModel>>>(DomainResult.Loading)
    val foodItems: StateFlow<DomainResult<List<BowlDomainModel>>>
        get() = _foodItems

    private fun getAllFoodItems() {
        viewModelScope.launch {
            getAllFoodItemsUseCase
                .getAllFoodItems()
                .collect {
                    _foodItems.value = it
                }
        }
    }
}
