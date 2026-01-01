package com.quoctran.pharmacies.presentation.ui.order

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.quoctran.pharmacies.presentation.components.PriceTag
import com.quoctran.pharmacies.presentation.model.ProductUI
import kotlin.math.roundToInt


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderScreen(
    modifier: Modifier = Modifier, orderViewModel: OrderViewModel = hiltViewModel(),
    onCreateNewOrder: () -> Unit
) {
    val products by orderViewModel.products.collectAsState()
    val cartItems by orderViewModel.cartItems.collectAsState()
    val sheetState = rememberModalBottomSheetState()
    var showSheet by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.background(color = Color(0xFFF5F5F5)),
        bottomBar = {
            if (cartItems.isNotEmpty()) {
                QuickOrderBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    totalItems = cartItems.sumOf { it.quantity },
                    totalPrice = cartItems.sumOf { it.price * it.quantity },
                    onClick = { showSheet = true }
                )
            }
        }
    ) { padding ->
        ProductList(
            modifier = Modifier.padding(padding), products, orderViewModel::addToCart,
            orderViewModel::removeFromCart
        )
    }

    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = { showSheet = false },
            sheetState = sheetState
        ) {
            QuickOrderSummaryContent(
                items = cartItems,
                onCheckout = {
                    // Handle checkout action
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductList(
    modifier: Modifier = Modifier,
    products: List<ProductUI>,
    addToCart: (ProductUI) -> Unit,
    removeFromCart: (ProductUI) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf<String?>(null) }
    var priceRange by remember { mutableStateOf(100000f) }

    val categories = products.map { it.category }.distinct()

    val filteredProducts = products.filter {
        val matchesSearch = it.name.contains(searchQuery, ignoreCase = true)
        val matchesCategory = selectedCategory == null || it.category == selectedCategory
        val matchesPrice = it.price <= priceRange
        matchesSearch && matchesCategory && matchesPrice
    }

    Column(modifier = modifier) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Search products") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp),
            singleLine = true
        )

        LazyRow(modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)) {
            item {
                FilterChip(
                    selected = selectedCategory == null,
                    onClick = { selectedCategory = null },
                    label = { Text("All") },
                    modifier = Modifier.padding(end = 4.dp)
                )
            }
            items(categories.size) { index ->
                val category = categories[index]
                FilterChip(
                    selected = selectedCategory == category,
                    onClick = { selectedCategory = category },
                    label = { Text(category) },
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
            }
        }

        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
            PriceTag(amount = priceRange.toLong(), stringFirst = "Max Price: ")
            Slider(
                steps = 100,
                value = priceRange,
                onValueChange = {
                    priceRange = (it / 100).roundToInt() * 100f
                },
                valueRange = 0f..200000f,
                modifier = Modifier.height(20.dp)
            )
        }


        if (filteredProducts.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("No products found matching your criteria.")
            }
        } else {
            LazyColumn {
                items(filteredProducts.size) { index ->
                    ProductItem(
                        product = filteredProducts[index],
                        addToCart = addToCart,
                        removeFromCart = removeFromCart
                    )
                }
            }
        }
    }
}


@Composable
fun ProductItem(
    modifier: Modifier = Modifier,
    product: ProductUI,
    addToCart: (ProductUI) -> Unit,
    removeFromCart: (ProductUI) -> Unit
) {
    val color: Color = when (product.is_prescription) {
        "TRUE" -> Color(0xFFE3F2FD)
        "FALSE" -> Color(0xFFFFFFFF)
        else -> Color(0xFFFFFFFF)
    }

    Surface(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
            .background(Color.White)
            .fillMaxWidth()
            .padding(8.dp),
        color = color
    ) {
        Column {
            Text(
                text = product.name,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = product.category,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                PriceTag(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    amount = product.price,
                    stringFirst = "Price: "
                )
            }


            Row {
                IconButton(onClick = {
                    removeFromCart(product)
                }) {
                    Icon(
                        imageVector = Icons.Default.Remove,
                        contentDescription = "Decrease"
                    )
                }

                Text("${product.quantity}", modifier = Modifier.padding(8.dp))

                IconButton(onClick = {
                    addToCart(product)
                }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Decrease"
                    )
                }
            }
        }
    }

}

@Composable
fun QuickOrderSummaryContent(
    modifier: Modifier = Modifier,
    items: List<ProductUI>,
    onCheckout: () -> Unit
) {
    LazyColumn {
        items(items.size) { index ->
            val product = items[index]
            Surface(
                shape = MaterialTheme.shapes.medium,
                modifier = modifier.padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = product.name,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Text("Quantity: ${product.quantity}", modifier = Modifier.padding(8.dp))
                        PriceTag(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            amount = product.price * product.quantity,
                            stringFirst = "Total: "
                        )
                    }

                }
            }
        }
    }
}

@Composable
fun QuickOrderBar(
    modifier: Modifier = Modifier,
    totalItems: Int,
    totalPrice: Long,
    onClick: () -> Unit
) {
    Surface(modifier = modifier) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = ("Quick Order"),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(8.dp)
                )
                IconButton(onClick = {
                    onClick()
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowUpward,
                        contentDescription = "Decrease"
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text("Total Item: $totalItems")
                PriceTag(amount = totalPrice, stringFirst = "Total Price: ")
            }
        }
    }
}


@Preview
@Composable
fun ProductItemPreview() {

}
