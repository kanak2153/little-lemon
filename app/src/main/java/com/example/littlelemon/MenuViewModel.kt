package com.example.littlelemon

import android.app.Application
import androidx.lifecycle.*

class MenuViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getDatabase(application)
    private val menuDao = db.menuDao()

    val menuItems: LiveData<List<MenuItemEntity>> = liveData {
        emit(menuDao.getAllMenuItems())
    }
}
