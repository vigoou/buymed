package com.quoctran.pharmacies.presentation.ui.order.create

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.quoctran.pharmacies.presentation.components.SearchableDropdown

@Composable
fun CreateOrderScreen(modifier: Modifier = Modifier, createOrderViewModel: CreateOrderViewModel = hiltViewModel()) {
    val products = createOrderViewModel.products.collectAsState()
    var customerName by rememberSaveable { mutableStateOf("") }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Create New Order",
            style = MaterialTheme.typography.headlineMedium
        )

        OutlinedTextField(
            value = customerName,
            onValueChange = { customerName = it },
            label = { Text("Customer Name") },
            placeholder = { Text("Enter customer name") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        SearchableDropdown(options = products.value) { query ->
            // Handle search query
        }
    }
}
