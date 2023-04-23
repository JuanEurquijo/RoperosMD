package org.minutodedios.roperos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth
import org.minutodedios.roperos.navigation.RoperosNavigation
import org.minutodedios.roperos.ui.theme.AppRoperosMDTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppRoperosMDTheme {
                RoperosApp()
            }
        }
    }
}

@Composable
fun RoperosApp(){
    Surface(modifier = Modifier
        .fillMaxSize()
        .padding(top = 40.dp),
        color = MaterialTheme.colorScheme.background
    ) {
      Column(
          verticalArrangement = Arrangement.Center,
          horizontalAlignment = Alignment.CenterHorizontally
      ) {
          RoperosNavigation()
      }
    }
}