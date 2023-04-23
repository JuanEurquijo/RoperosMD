package org.minutodedios.roperos.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import org.minutodedios.roperos.ui.screens.start.StartScreen
import org.minutodedios.roperos.ui.theme.ApplicationTheme

@AndroidEntryPoint
class StartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApplicationTheme {
                StartScreen {
                    startActivity(Intent(this, MainActivity::class.java))
                    this.finish();
                }
            }
        }
    }
}