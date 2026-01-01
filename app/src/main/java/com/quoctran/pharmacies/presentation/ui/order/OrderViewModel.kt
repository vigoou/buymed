package com.quoctran.pharmacies.presentation.ui.order

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
import kotlin.compareTo
import kotlin.text.set

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val getListProductUseCase: GetListProductUseCase
) : ViewModel() {

    private val _products = MutableStateFlow<List<ProductUI>>(emptyList())
    val products: StateFlow<List<ProductUI>> = _products.asStateFlow()

    private val _cartItems = MutableStateFlow<List<ProductUI>>(emptyList())
    val cartItems: StateFlow<List<ProductUI>> = _cartItems.asStateFlow()

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            getListProductUseCase(UseCase.None()) { productList ->
                _products.value = productList.map {  product ->
                    ProductUI.mapFromDomainToUI(product)
                }
            }
        }
    }

    fun addToCart(productUI: ProductUI) {
        val currentCart = _cartItems.value.toMutableList()
        val existingItemIndex = currentCart.indexOfFirst { it.id == productUI.id }
        if (existingItemIndex >= 0) {
            val existingItem = currentCart[existingItemIndex]
            if (existingItem.quantity < 99) {
                currentCart[existingItemIndex] =
                    existingItem.copy(quantity = existingItem.quantity + 1)
            }
        } else {
            currentCart.add(productUI.copy(quantity = 1))
        }
        _cartItems.value = currentCart
        updateCartItemQuantity(productUI, currentCart.find { it.id == productUI.id }!!.quantity)

    }

    fun removeFromCart(productUI: ProductUI) {
        val currentCart = _cartItems.value.toMutableList()
        val existingItemIndex = currentCart.indexOfFirst { it.id == productUI.id }
        if (existingItemIndex >= 0) {
            val existingItem = currentCart[existingItemIndex]
            if (existingItem.quantity > 1) {
                currentCart[existingItemIndex] = existingItem.copy(quantity = existingItem.quantity - 1)
            } else {
                currentCart.removeAt(existingItemIndex)
            }
            _cartItems.value = currentCart
        }
        currentCart.find { it.id == productUI.id }?.let { it ->
            updateCartItemQuantity(productUI, it.quantity)
        } ?: updateCartItemQuantity(productUI, 0)

    }
    fun updateCartItemQuantity(productUI: ProductUI, newQuantity: Int) {
        val currentCart = _products.value.toMutableList()
        val existingItemIndex = currentCart.indexOfFirst { it.id == productUI.id }
        if (existingItemIndex >= 0) {
            if (newQuantity > 0) {
                currentCart[existingItemIndex] = productUI.copy(quantity = newQuantity)
            } else {
                currentCart[existingItemIndex] = productUI.copy(quantity = 0)
            }
            _products.value = currentCart
        }
    }
}