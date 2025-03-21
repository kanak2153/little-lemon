package com.example.littlelemon

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@Composable
fun Profile(navController:NavController) {
    val context = LocalContext.current
    val sharedPreferences: SharedPreferences = remember {
        context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    }

    val firstName = sharedPreferences.getString("first_name", "N/A") ?: "N/A"
    val lastName = sharedPreferences.getString("last_name", "N/A") ?: "N/A"
    val email = sharedPreferences.getString("email", "N/A") ?: "N/A"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header (Logo)
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "App Logo",
            modifier = Modifier.height(50.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Profile Information
        Text(
            text = "Profile Information:",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Display User Data
        Text(text = "First Name: $firstName", fontSize = 18.sp)
        Text(text = "Last Name: $lastName", fontSize = 18.sp)
        Text(text = "Email: $email", fontSize = 18.sp)

        Button(
            onClick = {
                sharedPreferences.edit().clear().apply()
                navController.navigate("onboarding") {
                    popUpTo("profile") { inclusive = true }
                }
            }
        ) {
            Text("Log out")
        }
    }
}


