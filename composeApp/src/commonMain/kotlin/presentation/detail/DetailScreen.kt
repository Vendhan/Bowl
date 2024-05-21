package presentation.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import domain.model.BowlDomainModel
import domain.util.DomainResult
import kotlinx.coroutines.launch
import presentation.util.koinViewModel

@Composable
fun DetailScreen(
    navigateBack: () -> Unit,
    foodID: Int,
) {
    println("Details Screen ID: $foodID")
    val detailViewModel = koinViewModel<DetailViewModel>()
    LaunchedEffect(key1 = foodID) {
        detailViewModel.getFoodItem(foodID)
    }
    var isFavorite by remember { mutableStateOf(false) }
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail") },
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    detailViewModel.updateFavorite(foodID = foodID, isFavorite = !isFavorite)
                    isFavorite = !isFavorite
                    coroutineScope.launch {
                        scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
                        if (isFavorite) {
                            scaffoldState.snackbarHostState.showSnackbar(
                                message = "Added to favorite",
                            )
                        } else {
                            scaffoldState.snackbarHostState.showSnackbar(
                                message = "Removed from favorite",
                            )
                        }
                    }
                },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White,
                content = {
                    if (isFavorite) {
                        Icon(
                            Icons.Default.Favorite,
                            contentDescription = "Back",
                            tint = Color.Red,
                        )
                    } else {
                        Icon(Icons.Default.FavoriteBorder, contentDescription = "Back")
                    }
                },
            )
        },
        scaffoldState = scaffoldState,
    ) {
        val foodItem by detailViewModel.foodItem.collectAsState()
        when (foodItem) {
            is DomainResult.Error -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = (foodItem as DomainResult.Error).message,
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
                isFavorite = (foodItem as DomainResult.Success<BowlDomainModel>).data.isFavorite
                DetailScreenContent(
                    foodItem = (foodItem as DomainResult.Success<BowlDomainModel>).data,
                )
            }
        }
    }
}

@Composable
fun DetailScreenContent(foodItem: BowlDomainModel) {
    LazyColumn(
        modifier =
            Modifier
                .padding(16.dp),
    ) {
        item {
            AsyncImage(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(320.dp)
                        .clip(shape = RoundedCornerShape(8.dp)),
                model = foodItem.imageUrl,
                contentDescription = "Food item image",
                contentScale = ContentScale.FillBounds,
                onError = {
                    // Handle error
                },
                onLoading = {
                    // Handle loading
                },
            )
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = foodItem.name,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.SemiBold,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = foodItem.description,
                style = MaterialTheme.typography.body1,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = foodItem.origin,
                style = MaterialTheme.typography.body1,
                color = Color.Gray,
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            Text(
                text = "Cooking Instructions",
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.SemiBold,
            )
            Spacer(modifier = Modifier.height(4.dp))
        }
        itemsIndexed(foodItem.cookingInstructions) { index, cookingInstruction ->
            Text("${index.plus(1)}. $cookingInstruction")
            Spacer(modifier = Modifier.height(2.dp))
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "History",
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.SemiBold,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = foodItem.history,
                style = MaterialTheme.typography.body1,
            )
        }
    }
}
