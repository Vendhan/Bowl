package presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import presentation.detail.DetailScreen
import presentation.main.MainScreen

@Composable
fun BowlNavigationHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Constants.MAIN_ROUTE,
    ) {
        composable(route = Constants.MAIN_ROUTE) {
            MainScreen(
                navigateToDetailScreen = { foodID ->
                    navController.navigate(
                        route = Constants.DETAIL_ROUTE + "?${Constants.DETAIL_ROUTE_ID}=$foodID",
                    )
                },
            )
        }
        composable(
            route = Constants.DETAIL_ROUTE + "?${Constants.DETAIL_ROUTE_ID}={${Constants.DETAIL_ROUTE_ID}}",
            arguments =
                listOf(
                    navArgument(name = Constants.DETAIL_ROUTE_ID) {
                        this.type = NavType.IntType
                        this.defaultValue = -1
                        this.nullable = false
                    },
                ),
        ) { navBackStackEntry ->
            DetailScreen(
                navigateBack = {
                    navController.popBackStack()
                },
                foodID = navBackStackEntry.arguments?.getInt(Constants.DETAIL_ROUTE_ID) ?: -1,
            )
        }
//        composable(route = Constants.DETAIL_ROUTE) {
//            DetailScreen()
//        }
    }
}
