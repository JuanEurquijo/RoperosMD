package org.minutodedios.roperos.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import org.minutodedios.roperos.services.authentication.IAuthenticationService
import org.minutodedios.roperos.ui.screens.start.StartScreen
import org.minutodedios.roperos.ui.theme.ApplicationTheme
import javax.inject.Inject

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