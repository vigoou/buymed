package com.quoctran.pharmacies.presentation.base

import com.quoctran.pharmacies.presentation.ui.order.create.CreateOrderScreen
import com.quoctran.pharmacies.presentation.ui.order.OrderScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainNavGraph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "order_screen"
    ) {
        composable("order_screen") {
            OrderScreen(
                modifier = modifier,
                onCreateNewOrder = { navController.navigate("create_order_screen") }
            )
        }
        composable("create_order_screen") {
            CreateOrderScreen(modifier = modifier)
        }
    }
}