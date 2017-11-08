package ru.sukharev.myrecipes.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import ru.sukharev.myrecipes.pojo.Ingredient;
import ru.sukharev.myrecipes.pojo.Recipe;
import ru.sukharev.myrecipes.pojo.RecipeIngredient;

/**
 * Class describes database, which stores all recipes
 */
@Database(entities = {Recipe.class, RecipeIngredient.class, Ingredient.class}, version = 1)
public abstract class RecipeDatabase extends RoomDatabase {
}
