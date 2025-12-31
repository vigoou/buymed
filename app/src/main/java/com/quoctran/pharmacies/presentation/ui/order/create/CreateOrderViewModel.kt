package com.quoctran.pharmacies.presentation.ui.order.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quoctran.pharmacies.domain.base.UseCase
import com.quoctran.pharmacies.domain.usecase.GetListProductUseCase
import com.quoctran.pharmacies.presentation.model.ProductUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateOrderViewModel @Inject constructor(
private val getListProductUseCase: GetListProductUseCase
) : ViewModel() {

    private val _products = MutableStateFlow<List<ProductUI>>(emptyList())
    val products: StateFlow<List<ProductUI>> = _products.asStateFlow()

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            getListProductUseCase(UseCase.None()) { productList ->
                _products.value = productList.map { product ->
                    ProductUI.mapFromDomainToUI(product)
                }
            }
        }
    }
}