package com.example.littlelemon

import androidx.room.*

@Entity(tableName = "menu_items")

data class MenuItemEntity(
    @PrimaryKey val id :Int,
    val title: String,
    val description: String,
    val price: Int,
    val imageUrl: String
)

@Dao
interface MenuDao {
    @Query("SELECT * FROM menu_items")
    suspend fun getAllMenuItems(): List<MenuItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMenuItems(menuItems: List<MenuItemEntity>)
}

@Database(entities = [MenuItemEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun menuDao(): MenuDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: android.content.Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "littlelemon_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}