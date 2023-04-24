package org.minutodedios.roperos.ui.screens.login

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.minutodedios.roperos.AuthViewModel
import org.minutodedios.roperos.R
import org.minutodedios.roperos.services.authentication.mock.MockAuthenticationService
import org.minutodedios.roperos.ui.theme.ApplicationTheme

@Composable
fun LoginScreen(
    authViewModel: AuthViewModel = viewModel(),
    initialStateIsLogin: Boolean = true
) {
    var mustShowLogin by remember { mutableStateOf(initialStateIsLogin) }

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "",
                modifier = Modifier.size(width = 150.dp, height = 150.dp)
            )

            Text(
                text = (if (mustShowLogin) "Iniciar Sesión" else "Crea una cuenta"),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                ), fontSize = 30.sp
            )

            UserForm(
                isCreatingAccount = !mustShowLogin
            ) { email, password ->
                // TODO: Mostrar los errores
                if (mustShowLogin) {
                    // Inicio de sesión
                    authViewModel.authService.login(email, password) {
                        Log.d(
                            authViewModel.authService::class.simpleName,
                            "[$it] Resultado de inicio de sesión con la cuenta: $email"
                        )
                    }
                } else {
                    authViewModel.authService.register(email, password) {
                        Log.d(
                            authViewModel.authService::class.simpleName,
                            "[$it] Creación de la cuenta: $email"
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(15.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val text1 =
                    if (mustShowLogin) "¿Aún no tienes cuenta?"
                    else "¿Ya tienes una cuenta?"
                val text2 =
                    if (mustShowLogin) "Regístrate"
                    else "Inicia Sesión"
                Text(text = text1)
                Text(text = text2,
                    modifier = Modifier
                        .clickable { mustShowLogin = !mustShowLogin }
                        .padding(start = 5.dp),
                    color = Color.Blue
                )
            }
        }
    }
}

@Preview
@Composable
fun SignInScreenPreview() {
    ApplicationTheme {
        LoginScreen(AuthViewModel(MockAuthenticationService()), initialStateIsLogin = true)
    }
}

@Preview
@Composable
fun SignUpScreenPreview() {
    ApplicationTheme {
        LoginScreen(AuthViewModel(MockAuthenticationService()), initialStateIsLogin = false)
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UserForm(
    isCreatingAccount: Boolean = false,
    onDone: (String, String) -> Unit
) {
    val email = rememberSaveable {
        mutableStateOf("")
    }
    val password = rememberSaveable {
        mutableStateOf("")
    }

    val passwordVisible = rememberSaveable {
        mutableStateOf(false)
    }

    val valid = remember(email.value, password.value) {
        email.value.trim().isNotEmpty() &&
                password.value.trim().isNotEmpty()
    }

    val keyboardController = LocalSoftwareKeyboardController.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(20.dp)
    ) {
        EmailInput(
            emailState = email
        )

        PasswordInput(
            passwordState = password,
            labelId = "Contraseña",
            passwordVisible = passwordVisible
        )

        Spacer(modifier = Modifier.height(15.dp))

        SubmitInput(
            textId = if (isCreatingAccount) "Crear Cuenta" else "Iniciar Sesión",
            validInput = valid
        ) {
            onDone(email.value.trim(), password.value.trim())
            keyboardController?.hide()
        }
    }
}

@Composable
fun SubmitInput(
    textId: String,
    validInput: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(1.dp)
            .fillMaxWidth(),
        shape = CircleShape,
        enabled = validInput
    ) {
        Text(
            text = textId,
            modifier = Modifier
                .padding(5.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordInput(
    passwordState: MutableState<String>,
    labelId: String,
    passwordVisible: MutableState<Boolean>
) {
    val visualTransformation = if (passwordVisible.value)
        VisualTransformation.None
    else PasswordVisualTransformation()
    OutlinedTextField(
        value = passwordState.value,
        onValueChange = { passwordState.value = it },
        label = { Text(text = labelId) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ), modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth(),
        visualTransformation = visualTransformation,
        trailingIcon = {
            if (passwordState.value.isNotBlank()) {
                PasswordVisibleIcon(passwordVisible)
            }
        }
    )
}

@Composable
fun PasswordVisibleIcon(
    passwordVisible: MutableState<Boolean>
) {
    val image =
        if (passwordVisible.value)
            Icons.Default.VisibilityOff
        else
            Icons.Default.Visibility
    IconButton(onClick = {
        passwordVisible.value = !passwordVisible.value
    }) {
        Icon(
            imageVector = image,
            contentDescription = ""
        )
    }
}

@Composable
fun EmailInput(
    emailState: MutableState<String>,
    labelId: String = "Correo Electrónico"
) {
    InputField(
        valueState = emailState,
        labelId = labelId,
        keyboardType = KeyboardType.Email
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    valueState: MutableState<String>,
    labelId: String,
    isSingleLine: Boolean = true,
    keyboardType: KeyboardType
) {
    OutlinedTextField(
        value = valueState.value,
        onValueChange = { valueState.value = it },
        label = { Text(text = labelId) },
        singleLine = isSingleLine,
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        )
    )
}
