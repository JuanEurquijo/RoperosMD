package org.minutodedios.roperos.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import org.minutodedios.roperos.ui.screens.start.StartScreen
import org.minutodedios.roperos.ui.theme.ApplicationTheme

/**
 * Actividad inicial donde se muestra el logo de la corporación
 */
@AndroidEntryPoint
class StartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ApplicationTheme {
                StartScreen {
                    startActivity(Intent(this, RootActivity::class.java))
                    this.finish()
                }
            }
        }
    }
}