package org.minutodedios.roperos.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.minutodedios.roperos.navigation.routes.ActionsNavigationRoute
import org.minutodedios.roperos.ui.theme.ApplicationTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActionMenuItem(navController: NavHostController, item: ActionsNavigationRoute) {
    Card(
        modifier = Modifier.height(150.dp),
        onClick = {
            navController.navigate(item.route)
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = item.background),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = item.title,
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 4.dp)
                    .wrapContentSize(),
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = item.foreground
                )
            )
            Image(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxHeight()
                    .height(IntrinsicSize.Min),
                painter = painterResource(id = item.icon),
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                colorFilter = ColorFilter.tint(item.foreground)
            )
        }
    }
}

@Composable
@Preview(widthDp = 200, name = "OutRoute")
fun ActionMenuItemOutPreview() {
    ApplicationTheme {
        ActionMenuItem(
            navController = rememberNavController(),
            ActionsNavigationRoute.OutRoute
        )
    }
}

@Composable
@Preview(widthDp = 200, name = "InRoute")
fun ActionMenuItemInPreview() {
    ApplicationTheme {
        ActionMenuItem(
            navController = rememberNavController(),
            ActionsNavigationRoute.InRoute
        )
    }
}

@Composable
@Preview(widthDp = 200, name = "HistoryRoute")
fun ActionMenuItemHistoryPreview() {
    ApplicationTheme {
        ActionMenuItem(
            navController = rememberNavController(),
            ActionsNavigationRoute.HistoryRoute
        )
    }
}

@Composable
@Preview(widthDp = 200, name = "InventoryRoute")
fun ActionMenuItemInventoryPreview() {
    ApplicationTheme {
        ActionMenuItem(
            navController = rememberNavController(),
            ActionsNavigationRoute.InventoryRoute
        )
    }
}