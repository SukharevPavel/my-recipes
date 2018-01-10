package ru.sukharev.myrecipes.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.VisibleForTesting;

import ru.sukharev.myrecipes.database.dao.RecipeDao;
import ru.sukharev.myrecipes.pojo.Ingredient;
import ru.sukharev.myrecipes.pojo.Recipe;
import ru.sukharev.myrecipes.pojo.RecipeIngredient;

/**
 * Class describes database, which stores all recipes
 */
@Database(entities = {Recipe.class, RecipeIngredient.class, Ingredient.class}, version = 1)
public abstract class RecipeDatabase extends RoomDatabase {

    public final static String DATABASE_NAME = "RecipeDatabase";
    private static RecipeDatabase instance;

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    public static void setDatabase(RecipeDatabase database) {
        instance = database;
    }

    public static RecipeDatabase getInstance(Context context){
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    RecipeDatabase.class,
                    DATABASE_NAME).build();
        }
        return instance;
    }

    public abstract RecipeDao getRecipeDao();
}
