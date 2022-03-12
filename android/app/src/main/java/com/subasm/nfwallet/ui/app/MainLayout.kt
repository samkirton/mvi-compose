package com.subasm.nfwallet.ui.app

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.subasm.nfwallet.ui.app.entry.EntryLayout
import com.subasm.nfwallet.ui.app.token.fungible.FungibleTokenLayout
import com.subasm.nfwallet.ui.app.wallet.createaddress.CreateAddressLayout
import com.subasm.nfwallet.ui.app.wallet.importkey.ImportAddressLayout

@Composable
fun MainLayout() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            if (!listOf(
                    AddressNavigationItem.Entry.route,
                    AddressNavigationItem.CreateAddress.route,
                    AddressNavigationItem.ImportAddress.route
                ).contains(currentRoute(navController))
            ) {
                MainBottomNavigation(navController = navController)
            }
        }
    ) {
        NavHost(navController, startDestination = AddressNavigationItem.Entry.route) {
            composable(AddressNavigationItem.Entry.route) {
                EntryLayout(navController)
            }
            composable(AddressNavigationItem.CreateAddress.route) {
                CreateAddressLayout(hiltViewModel(), navController)
            }
            composable(AddressNavigationItem.ImportAddress.route) {
                ImportAddressLayout(hiltViewModel(), navController)
            }
            composable(AddressNavigationItem.Fungible.route) {
                FungibleTokenLayout(hiltViewModel(), navController)
            }
            composable(AddressNavigationItem.NonFungible.route) {
                Text("nft")
            }
            composable(AddressNavigationItem.Settings.route) {
                Text("settings")
            }
        }
    }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}
