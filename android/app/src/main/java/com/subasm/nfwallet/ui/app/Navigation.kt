package com.subasm.nfwallet.ui.app

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.subasm.nfwallet.R

enum class AddressNavigationItem(
    val route: String
) {
    Entry("entry"),
    CreateAddress("create_address"),
    ImportAddress("import_address"),
    EntrySettings("entry_settings"),
    Fungible("fungible"),
    NonFungible("nft"),
    Settings("settings")
}

@Composable
fun MainBottomNavigation(navController: NavHostController) {
    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color.Black
    ) {
        AddressBottomNavigationItem(
            AddressNavigationItem.Fungible.route,
            {
                Icon(
                    painter = painterResource(R.drawable.ic_xrp_coin),
                    contentDescription = stringResource(R.string.address_navigation_fungible),
                    modifier = Modifier.size(24.dp)
                )
            },
            navController
        )
        AddressBottomNavigationItem(
            AddressNavigationItem.NonFungible.route,
            {
                Icon(
                    painter = painterResource(R.drawable.ic_poker_cards_24),
                    contentDescription = stringResource(R.string.address_navigation_non_fungible)
                )
            },
            navController
        )
        AddressBottomNavigationItem(
            AddressNavigationItem.Settings.route,
            {
                Icon(
                    imageVector = Icons.Outlined.Settings,
                    contentDescription = stringResource(R.string.address_navigation_settings)
                )
            },
            navController
        )
    }
}

@Composable
fun RowScope.AddressBottomNavigationItem(
    route: String,
    icon: @Composable () -> Unit,
    navController: NavHostController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    BottomNavigationItem(
        icon = icon,
        selectedContentColor = MaterialTheme.colors.primary,
        unselectedContentColor = Color.Black.copy(0.4f),
        alwaysShowLabel = true,
        selected = currentRoute == route,
        onClick = {
            navController.navigate(route) {
                navController.graph.startDestinationRoute?.let { screen_route ->
                    popUpTo(screen_route) {
                        saveState = true
                    }
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    )
}
