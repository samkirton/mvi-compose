package com.subasm.nfgen.ui.app

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
import com.subasm.nfgen.ui.app.entry.EntryLayout
import com.subasm.nfgen.ui.app.token.fungible.FungibleTokenLayout
import com.subasm.nfgen.ui.app.token.nft.NonFungibleTokenLayout
import com.subasm.nfgen.ui.app.wallet.createaddress.CreateAddressLayout
import com.subasm.nfgen.ui.app.wallet.importkey.ImportAddressLayout

@Composable
fun MainLayout() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            if (!listOf(
                    NavigationItem.Entry.route,
                    NavigationItem.CreateAddress.route,
                    NavigationItem.ImportAddress.route
                ).contains(currentRoute(navController))
            ) {
                MainBottomNavigation(navController = navController)
            }
        }
    ) {
        NavHost(navController, startDestination = NavigationItem.Entry.route) {
            composable(NavigationItem.Entry.route) {
                EntryLayout(navController)
            }
            composable(NavigationItem.CreateAddress.route) {
                CreateAddressLayout(hiltViewModel(), navController)
            }
            composable(NavigationItem.ImportAddress.route) {
                ImportAddressLayout(hiltViewModel(), navController)
            }
            composable(NavigationItem.Fungible.route) {
                FungibleTokenLayout(hiltViewModel())
            }
            composable(NavigationItem.NonFungible.route) {
                NonFungibleTokenLayout(hiltViewModel(), navController)
            }
            composable(NavigationItem.MintNonFungible.route) {
                Text("mint")
            }
            composable(NavigationItem.Settings.route) {
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
