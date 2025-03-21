package com.example.littlelemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.littlelemon.ui.theme.LittlelemonTheme
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class MainActivity : ComponentActivity() {

    private val httpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
            })
        }
    }

    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "littlelemon_database").build()

        lifecycleScope.launch {
            val menuItems = MenuNetworkData(httpClient).fetchMenu()
            database.menuDao().insertMenuItems(menuItems.map {
                MenuItemEntity(
                    id = it.id,
                    title = it.title,
                    description = it.description,
                    price = it.price,
                    imageUrl = it.imageUrl
                )
            }

            )
        }

        setContent {
            LittlelemonTheme {
                val navController = rememberNavController()
                Navigation(navController, database)
            }
        }
    }
}
