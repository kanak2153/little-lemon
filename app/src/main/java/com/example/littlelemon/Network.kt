package com.example.littlelemon

import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.request.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json


val httpClient = HttpClient {
   install(ContentNegotiation) {
      json(Json { ignoreUnknownKeys = true })
   }
}


@Serializable
data class MenuNetwork(
   val menu: List<MenuItemNetwork>
)


@Serializable
data class MenuItemNetwork(
   val id: Int,
   val title: String,
   val description: String,
   val price: Int,
   @SerialName("image") val imageUrl: String
)


class MenuNetworkData(private val httpClient: HttpClient) {
   suspend fun fetchMenu(): List<MenuItemNetwork> {
      return try {
         val response: MenuNetwork = httpClient.get("https://your-api-url.com/menu").body()
         response.menu
      } catch (e: Exception) {
         emptyList()
      }
   }
}
