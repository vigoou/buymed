package com.quoctran.pharmacies.presentation.ui.order

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.quoctran.pharmacies.presentation.model.ProductUI
import androidx.compose.material3.Icon

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
        bottomBar = {
            if (cartItems.isNotEmpty()) {
                QuickOrderBar(
                    totalItems = cartItems.sumOf { it.quantity },
                    totalPrice = cartItems.sumOf { it.price * it.quantity },
                    onClick = { showSheet = true }
                )
            }
        }
    ) { padding ->
        // 1. View a list of products + 2. Search & Filter
        ProductList(modifier = Modifier.padding(padding), products)
    }

    // 3. Quick Order Summary (Bottom Sheet)
    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = { showSheet = false },
            sheetState = sheetState
        ) {
            QuickOrderSummaryContent(
                items = cartItems,
                onCheckout = { /* Xử lý thanh toán */ }
            )
        }
    }

//    orderViewModel.products.collectAsState().value.let { product ->
//        Log.d("OrderScreen", "product: $product")
//    }
//
//    Scaffold(
//        modifier = modifier,
//        floatingActionButton = {
//            FloatingActionButton(
//                onClick = onCreateNewOrder
//            ) {
//                Icon(
//                    imageVector = Icons.Default.Add,
//                    contentDescription = "Create New Order"
//                )
//            }
//        }
//    ) { paddingValues ->
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(paddingValues),
//            contentAlignment = Alignment.Center
//        ) {
//            Text(
//                text = "Hello !"
//            )
//        }
//    }
}

@Composable
fun ProductList(modifier: Modifier = Modifier, products: List<ProductUI>) {
    LazyColumn(modifier = modifier) {
        items(products.size) { index ->
            ProductItem(product = products[index])
        }

    }
}

@Composable
fun ProductItem(modifier: Modifier = Modifier, product: ProductUI) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
    ) {
        Column {
            Text(
                text = product.name,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Text(
                text = product.category,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Row {
                IconButton(onClick = {

                }) {
                    Icon(imageVector = Icons.Default.Su, contentDescription = null)
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

}

@Composable
fun QuickOrderBar(
    modifier: Modifier = Modifier,
    totalItems: Int,
    totalPrice: Long,
    onClick: () -> Unit
) {


}


@Preview
@Composable
fun ProductItemPreview() {

    ProductItem(
        product = ProductUI(
            id = 1,
            name = stringResource(id = com.quoctran.pharmacies.R.string.app_name),
            price = 10000,
            imgUrl = "",
            quantity = 2,
            category = "Xxxxxxxx"
        )
    )
}