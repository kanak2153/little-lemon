package com.example.littlelemon

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun Onboarding(navController: NavController) {
    val context = LocalContext.current
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    Column {
        // Header Image
        Image(
            modifier = Modifier
                .height(60.dp)
                .padding(start = 20.dp)
                .fillMaxWidth(),
            painter = painterResource(R.drawable.littlelemonheader),
            contentDescription = "header"
        )

        // Title Text
        Text(
            modifier = Modifier
                .background(color = Color(0xFF495E57))
                .height(80.dp)
                .fillMaxWidth()
                .padding(top = 25.dp),
            text = "Let's get to know you",
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = 24.sp
        )

        Box(modifier = Modifier.padding(20.dp)) {
            Column {
                Text("Personal Information")

                // Input Fields
                TextField(
                    modifier = Modifier.padding(vertical = 10.dp),
                    value = firstName,
                    onValueChange = { firstName = it },
                    label = { Text("First Name") }
                )
                TextField(
                    modifier = Modifier.padding(vertical = 10.dp),
                    value = lastName,
                    onValueChange = { lastName = it },
                    label = { Text("Last Name") }
                )
                TextField(
                    modifier = Modifier.padding(vertical = 10.dp),
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Please enter your email") }
                )

                // Registration Button
                Button(
                    onClick = {
                        if (firstName.isBlank() || lastName.isBlank() || email.isBlank()) {
                            message = "Registration unsuccessful. Please enter all data."
                        } else {
                          
                            sharedPreferences.edit()
                                .putString("first_name", firstName)
                                .putString("last_name", lastName)
                                .putString("email", email)
                                .putBoolean("is_first_time", false)  
                                .apply()

                            message = "Registration Successful!"
                            navController.navigate("Home")
                        }
                    },
                    modifier = Modifier
                        .padding(top = 40.dp)
                        .fillMaxWidth()
                ) {
                    Text("Register")
                }

                // Display Message
                if (message.isNotEmpty()) {
                    Text(
                        text = message,
                        color = if (message.contains("unsuccessful")) Color.Red else Color.Green,
                        modifier = Modifier.padding(top = 20.dp)
                    )
                }
            }
        }
    }
}
