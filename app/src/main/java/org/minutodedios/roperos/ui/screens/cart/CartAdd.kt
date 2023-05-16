package org.minutodedios.roperos.ui.screens.cart

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.runBlocking
import org.minutodedios.roperos.model.Category
import org.minutodedios.roperos.services.database.MockDatabaseService
import org.minutodedios.roperos.ui.theme.ApplicationTheme
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartAdd(
    navController: NavHostController,
    categories: List<Category>, selected: (CartEntry) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        categories.forEach { category ->
            Card(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = category.category.capitalize(Locale.ROOT),
                        style = TextStyle(fontSize = 26.sp)
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    category.subcategories.forEach { subcategory ->
                        ListItem(
                            modifier = Modifier
                                .padding(2.dp)
                                .clip(CircleShape)
                                .clickable {
                                    selected.invoke(
                                        CartEntry(
                                            category.category,
                                            subcategory,
                                            1
                                        )
                                    )
                                    navController.popBackStack()
                                },
                            headlineText = {
                                Text(
                                    text = subcategory.subcategory.capitalize(Locale.ROOT),
                                    style = TextStyle(fontSize = 16.sp)
                                )
                            }, trailingContent = {
                                Icon(Icons.Filled.NavigateNext, contentDescription = null)
                            })
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun AddToCartPreview() {
    ApplicationTheme {
        CartAdd(
            navController = rememberNavController(),
            categories = runBlocking { MockDatabaseService().inventoryForLocation("") }) {
        }
    }
}