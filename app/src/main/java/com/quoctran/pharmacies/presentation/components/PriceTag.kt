package com.quoctran.pharmacies.presentation.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import java.text.NumberFormat
import java.util.Locale

@Composable
fun PriceTag(modifier: Modifier = Modifier, amount: Long, stringFirst: String = "") {
    val formattedPrice = remember(amount) {
        formatCurrency(amount)
    }
    val displayText = if (stringFirst.isNotEmpty()) {
        "$stringFirst $formattedPrice"
    } else {
        formattedPrice
    }

    Text(
        modifier = modifier,
        text = displayText,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.primary
    )
}

fun formatCurrency(amount: Long): String {
    val localeVN = Locale("vi", "VN")
    val format = NumberFormat.getCurrencyInstance(localeVN)
    format.maximumFractionDigits = 0
    return format.format(amount)
}