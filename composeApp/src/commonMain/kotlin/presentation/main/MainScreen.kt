package presentation.main

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import presentation.navigation.HomeNavigationHost

@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController(),
    navigateToDetailScreen: (foodID: Int) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "The Bowl",
                        textAlign = TextAlign.Center,
                    )
                },
            )
        },
        bottomBar = { MainBottomNavigationBar(navController = navController) },
        content = { paddingValues ->
            HomeNavigationHost(
                navController = navController,
                navigateToDetailScreen = navigateToDetailScreen,
            )
        },
    )
}
