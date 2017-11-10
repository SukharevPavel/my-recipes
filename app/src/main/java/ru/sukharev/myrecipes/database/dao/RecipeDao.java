package ru.sukharev.myrecipes.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import ru.sukharev.myrecipes.pojo.Recipe;

/**
 * DAO for interacting with Recipe table
 */

@Dao
public interface RecipeDao {

    @Insert
    void addRecipe(Recipe recipe);

    @Query("Select * from Recipe")
    List<Recipe> getAllRecipes();
}
