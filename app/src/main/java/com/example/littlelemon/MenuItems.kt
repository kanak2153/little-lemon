package com.example.littlelemon

import kotlinx.serialization.Serializable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.GlideImage

@Composable
fun MenuItems(viewModel: MenuViewModel = viewModel()) {
    val menuItems by viewModel.menuItems.observeAsState(emptyList()) 

    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        items(menuItems) { menuItem ->
            MenuItemCard(menuItem) 
        }
    }
}


@Serializable
data class MenuItems(
    val id: Int,
    val title: String,
    val description: String,
    val price: Int,
    val image: String
)
