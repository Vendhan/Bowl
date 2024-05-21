package presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import domain.model.BowlDomainModel

@Composable
fun FoodList(
    modifier: Modifier = Modifier,
    state: LazyListState,
    foodList: List<BowlDomainModel>,
    navigateToDetailScreen: (foodID: Int) -> Unit,
) {
    LazyColumn(
        modifier =
            modifier
                .padding(horizontal = 8.dp),
        state = state,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(foodList, key = { it.id }) { foodItem ->
            FoodListItem(
                foodItem = foodItem,
                showFavorite = true,
                navigateToDetailScreen = navigateToDetailScreen,
            )
        }
    }
}
