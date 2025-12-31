package com.quoctran.pharmacies.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.quoctran.pharmacies.presentation.model.ProductUI

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchableDropdown(
    options: List<ProductUI>,
    onOptionSelected: (ProductUI) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }
    var currentSelect by remember { mutableStateOf<ProductUI?>(null) }

    val filteredOptions = options.filter {
        it.name.contains(searchQuery, ignoreCase = true)
    }

    Row(verticalAlignment =Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = it }
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = {
                    searchQuery = it
                    expanded = true
                },
                label = {
                    Text("Select Product")
                },
                modifier = Modifier.menuAnchor(),
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors()
            )

            if (filteredOptions.isNotEmpty()) {
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    filteredOptions.forEach { selection ->
                        DropdownMenuItem(
                            text = { Text(selection.name) },
                            onClick = {
                                searchQuery = selection.name
                                expanded = false
                                currentSelect = selection
                            }
                        )
                    }
                }
            }
        }

        Button(onClick = {
            currentSelect?.let {
                onOptionSelected(it)
            }

        }) {
            Text("Add")
        }
    }
}

@Preview
@Composable
fun SearchableDropdownPreview() {
    val sampleOptions = listOf(
        ProductUI(id = 1, name = "Aspirin", price = 5000, imgUrl = ""),
    )

    SearchableDropdown(
        options = sampleOptions,
        onOptionSelected = { selectedOption ->
            // Handle the selected option
        }
    )
}