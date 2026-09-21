package com.example.data.local

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PantryDao {
    @Query("SELECT * FROM pantry_items WHERE isFinished = 0 ORDER BY expiryDaysRemaining ASC, name ASC")
    fun getActivePantryItems(): Flow<List<PantryEntity>>

    @Query("SELECT * FROM pantry_items WHERE isFinished = 0 AND expiryDaysRemaining <= 3 ORDER BY expiryDaysRemaining ASC")
    fun getExpiringSoonItems(): Flow<List<PantryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(item: PantryEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<PantryEntity>)

    @Update
    suspend fun update(item: PantryEntity)

    @Delete
    suspend fun delete(item: PantryEntity)

    @Query("DELETE FROM pantry_items WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Query("UPDATE pantry_items SET isFinished = 1 WHERE id = :id")
    suspend fun markAsFinished(id: Long)

    @Query("SELECT * FROM pantry_items WHERE id = :id LIMIT 1")
    suspend fun getItemById(id: Long): PantryEntity?
}

@Dao
interface FavoritesDao {
    @Query("SELECT * FROM favorites ORDER BY dateAddedEpochMs DESC")
    fun getAllFavorites(): Flow<List<FavoriteEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE recipeId = :recipeId)")
    fun isFavorite(recipeId: String): Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(fav: FavoriteEntity)

    @Query("DELETE FROM favorites WHERE recipeId = :recipeId")
    suspend fun removeFavorite(recipeId: String)
}

@Dao
interface MealPlanDao {
    @Query("SELECT * FROM meal_plan ORDER BY id ASC")
    fun getWeeklyPlan(): Flow<List<MealPlanEntity>>

    @Query("SELECT * FROM meal_plan WHERE dayOfWeek = :day")
    fun getPlanForDay(day: String): Flow<List<MealPlanEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(meal: MealPlanEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(meals: List<MealPlanEntity>)

    @Delete
    suspend fun deleteMeal(meal: MealPlanEntity)

    @Query("DELETE FROM meal_plan WHERE dayOfWeek = :day AND mealType = :mealType")
    suspend fun removeMealSlot(day: String, mealType: String)

    @Query("DELETE FROM meal_plan")
    suspend fun clearAll()
}

@Dao
interface ShoppingDao {
    @Query("SELECT * FROM shopping_items ORDER BY isChecked ASC, category ASC, name ASC")
    fun getAllItems(): Flow<List<ShoppingItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: ShoppingItemEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<ShoppingItemEntity>)

    @Update
    suspend fun updateItem(item: ShoppingItemEntity)

    @Query("UPDATE shopping_items SET isChecked = :checked WHERE id = :id")
    suspend fun setChecked(id: Long, checked: Boolean)

    @Delete
    suspend fun deleteItem(item: ShoppingItemEntity)

    @Query("DELETE FROM shopping_items WHERE isChecked = 1")
    suspend fun clearCheckedItems()

    @Query("DELETE FROM shopping_items")
    suspend fun clearAll()
}

@Dao
interface CookingHistoryDao {
    @Query("SELECT * FROM cooking_history ORDER BY cookedDateEpochMs DESC")
    fun getHistory(): Flow<List<CookingHistoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecord(record: CookingHistoryEntity): Long

    @Query("SELECT COUNT(*) FROM cooking_history")
    fun getCookedCount(): Flow<Int>
}

@Dao
interface UserPreferencesDao {
    @Query("SELECT * FROM user_preferences WHERE id = 1 LIMIT 1")
    fun getPreferences(): Flow<UserPreferencesEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePreferences(prefs: UserPreferencesEntity)

    @Query("UPDATE user_preferences SET userName = :name WHERE id = 1")
    suspend fun updateUserName(name: String)
}

@Database(
    entities = [
        PantryEntity::class,
        FavoriteEntity::class,
        MealPlanEntity::class,
        ShoppingItemEntity::class,
        CookingHistoryEntity::class,
        UserPreferencesEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pantryDao(): PantryDao
    abstract fun favoritesDao(): FavoritesDao
    abstract fun mealPlanDao(): MealPlanDao
    abstract fun shoppingDao(): ShoppingDao
    abstract fun cookingHistoryDao(): CookingHistoryDao
    abstract fun userPreferencesDao(): UserPreferencesDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "pantry_chef.db"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}
