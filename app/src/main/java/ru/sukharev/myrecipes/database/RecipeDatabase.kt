package ru.sukharev.myrecipes.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import ru.sukharev.myrecipes.database.dao.RecipeDao
import ru.sukharev.myrecipes.pojo.Ingredient
import ru.sukharev.myrecipes.pojo.Recipe
import ru.sukharev.myrecipes.pojo.RecipeIngredient
import ru.sukharev.myrecipes.utils.SingletonHolder

/**
 * Class describes database, which stores all recipes
 */
@Database(entities = [(Recipe::class), (RecipeIngredient::class), (Ingredient::class)], version = 1)
abstract class RecipeDatabase : RoomDatabase() {

    abstract val recipeDao: RecipeDao

    companion object : SingletonHolder<RecipeDatabase, Context>({
        Room.databaseBuilder(it.applicationContext,
                RecipeDatabase::class.java,  RecipeDatabase.DATABASE_NAME)
                .build()
    }) {
        private const val DATABASE_NAME = "RecipeDatabase"
    }
}
