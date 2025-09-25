package com.team42.glassviewdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team42.glassviewdemo.customComponent.CustomTextField
import com.team42.glassviewdemo.customComponent.GlassMorphicCard
import com.team42.glassviewdemo.ui.theme.GlassViewDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GlassViewDemoTheme {
                LoginScreen()
            }
        }
    }
}

@Composable
fun LoginScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Background image
            Image(
                painter = painterResource(id = R.drawable.bg_city), // your bg image
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Glass View for login form
                GlassMorphicCard(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                ) {
                    // Place your login form here
                    LoginForm(
                        onLogin = { username, password -> },
                        onSignup = {}
                    )
                }
            }


        }
    }
}

@Composable
fun LoginForm(
    onLogin: (String, String) -> Unit = { _, _ -> },
    onSignup: () -> Unit = {}
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.5f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "User Icon",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(50.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        // LOGIN title
        Text(
            text = "LOGIN",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.SemiBold
            ),
            color = Color.White,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        // User name field
        Text(
            text = "User Name",
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Normal
            ),
            color = Color.White,
            textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.height(4.dp))
        CustomTextField(
            modifier = Modifier
                .padding(0.dp)
                .height(48.dp)
                .fillMaxWidth(),
            inputWrapper = username,
            placeHolder = "User name",
            onValueChange = {},
            borderColor = Color.White,
            isRoundedCorner = true,
            topStartRadius = 10.dp,
            topEndRadius = 0.dp,
            bottomStartRadius = 0.dp,
            bottomEndRadius = 10.dp,
            maxLines = 1,
            maxLength = 50,
        )
        Spacer(modifier = Modifier.height(6.dp))
        // Password field
        Text(
            text = "Password",
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Normal
            ),
            color = Color.White,
            textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.height(4.dp))
        CustomTextField(
            modifier = Modifier
                .padding(0.dp)
                .height(48.dp)
                .fillMaxWidth(),
            inputWrapper = password,
            placeHolder = "Password",
            onValueChange = {},
            borderColor = Color.White,
            visualTransformation = PasswordVisualTransformation(),
            keyBoardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            isRoundedCorner = true,
            topStartRadius = 10.dp,
            topEndRadius = 0.dp,
            bottomStartRadius = 0.dp,
            bottomEndRadius = 10.dp,
            maxLines = 1,
            maxLength = 50,
        )
        Spacer(modifier = Modifier.height(16.dp))
        // Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { onLogin(username, password) },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                shape = RectangleShape
            ) {
                Text(text = "Login", fontWeight = FontWeight.SemiBold)
            }

            Button(
                onClick = onSignup,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                )
            ) {
                Text(text = "Signup", fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    GlassViewDemoTheme {
        LoginScreen()
    }
}