package org.minutodedios.roperos.services.database

import org.minutodedios.roperos.model.Category
import org.minutodedios.roperos.model.Subcategory

class MockDatabaseService : IDatabaseService {
    override suspend fun inventoryForLocation(locationId: String): List<Category> =
        listOf(
            Category(
                "bebés",
                listOf(
                    Subcategory(
                        "chaquetas",
                        6000.0,
                        10
                    ),
                    Subcategory(
                        "camisas",
                        3000.0,
                        10
                    ),
                )
            ),
            Category(
                "mujeres",
                listOf(
                    Subcategory(
                        "pantalones",
                        6000.0,
                        15
                    ),
                    Subcategory(
                        "camisas",
                        12000.0,
                        6
                    ),
                )
            )
        )
}