package presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import domain.model.BowlDomainModel
import domain.util.DomainResult
import presentation.util.koinViewModel

@Composable
fun HomeScreen(navigateToDetailScreen: (foodID: Int) -> Unit) {
    val viewModel = koinViewModel<HomeViewModel>()
    val foodItems by viewModel.foodItems.collectAsState()
    val listState = rememberLazyListState()
    when (foodItems) {
        is DomainResult.Error -> {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = (foodItems as DomainResult.Error).message,
                    textAlign = TextAlign.Center,
                )
            }
        }
        DomainResult.Loading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                )
            }
        }
        is DomainResult.Success -> {
            FoodList(
                state = listState,
                foodList = (foodItems as DomainResult.Success<List<BowlDomainModel>>).data,
                navigateToDetailScreen = navigateToDetailScreen,
            )
        }
    }
}
