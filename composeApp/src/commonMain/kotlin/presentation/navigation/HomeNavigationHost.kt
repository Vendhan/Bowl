package presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import presentation.favorite.WishlistScreen
import presentation.home.HomeScreen

@Composable
fun HomeNavigationHost(
    navController: NavHostController = rememberNavController(),
    navigateToDetailScreen: (foodID: Int) -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = Constants.HOME_ROUTE,
    ) {
        composable(route = Constants.HOME_ROUTE) {
            HomeScreen(
                navigateToDetailScreen = navigateToDetailScreen,
            )
        }
        composable(route = Constants.WISHLIST_ROUTE) {
            WishlistScreen(
                navigateToDetailScreen = navigateToDetailScreen,
            )
        }
    }
}
