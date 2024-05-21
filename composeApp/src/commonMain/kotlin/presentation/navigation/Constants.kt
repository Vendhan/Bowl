package presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home

object Constants {
    const val MAIN_ROUTE = "main_screen"
    const val HOME_ROUTE = "home"
    const val WISHLIST_ROUTE = "wishlist"
    const val DETAIL_ROUTE = "detail"
    const val DETAIL_ROUTE_ID = "foodID"

    val mainScreenBottomNavigationItems =
        listOf(
            BottomNavigationItem(
                label = "Home",
                selectedIcon = Icons.Filled.Home,
                unSelectedIcon = Icons.Default.Home,
                route = HOME_ROUTE,
            ),
            BottomNavigationItem(
                label = "Favorites",
                selectedIcon = Icons.Filled.Favorite,
                unSelectedIcon = Icons.Default.Favorite,
                route = WISHLIST_ROUTE,
            ),
        )
}
