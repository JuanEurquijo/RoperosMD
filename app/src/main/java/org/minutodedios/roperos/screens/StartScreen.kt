package org.minutodedios.roperos.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.delay
import org.minutodedios.roperos.R
import org.minutodedios.roperos.navigation.RoperosScreens

@Composable
fun StartScreen(navController: NavController){
   val scale = remember {
      Animatable(0f)
   }
   LaunchedEffect(key1 = true){
      scale.animateTo(targetValue = 0.8f,
         animationSpec = tween(durationMillis = 1000,
         easing = {
            OvershootInterpolator(8f)
               .getInterpolation(it)
         }
            ),
      )
      delay(2000L)
      navController.navigate(RoperosScreens.LoginScreen.name)
      /*if (FirebaseAuth.getInstance().currentUser?.email.isNullOrEmpty()){
         navController.navigate(RoperosScreens.LoginScreen.name)
      } else {
         navController.navigate(RoperosScreens.StartScreen.name){
            popUpTo(RoperosScreens.StartScreen.name){
               inclusive = true
            }
         }
      }*/
   }
   Surface(modifier = Modifier
      .padding(10.dp)
      .size(300.dp)
      .scale(scale.value)
   ) {
      Image(
         painter = painterResource(id = R.drawable.brand),
         contentDescription = null
      )
   }

}