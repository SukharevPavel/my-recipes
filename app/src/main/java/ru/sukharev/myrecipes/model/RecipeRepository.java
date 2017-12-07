package ru.sukharev.myrecipes.model;

import android.content.Context;

import java.util.List;

import ru.sukharev.myrecipes.database.RecipeDatabase;
import ru.sukharev.myrecipes.database.dao.RecipeDao;
import ru.sukharev.myrecipes.pojo.Recipe;

/**
 * Class representing model for interacting with database
 */

public final class RecipeRepository implements IModel{

    private static RecipeRepository instance;
    private Context appContext;
    private RecipeDatabase database;

    private RecipeRepository(Context context) {
        this.appContext = context.getApplicationContext();
        database = RecipeDatabase.getInstance(appContext);
    }

    public static RecipeRepository getInstance(Context context){
        if (instance == null) {
            instance = new RecipeRepository(context);
        }
        return instance;
    }

    @Override
    public void getRecipes(final GetRecipesCallback callback) {
        RecipeDao.GetRecipesListTask task = new RecipeDao.GetRecipesListTask() {
            @Override
            public void result(List<Recipe> recipes) {
                callback.onResult(recipes);
            }
        };
        task.setDao(database.getRecipeDao())
                .execute();
    }

    @Override
    public void addRecipe(Recipe recipe, final AddRecipeCallback callback) {
        RecipeDao.AddRecipeAsyncTask task = new RecipeDao.AddRecipeAsyncTask() {
            @Override
            public void result(long result) {
                if (result >= 0) {
                    callback.onSuccess(result);
                } else {
                    callback.onFail();
                }
            }

        };
        task.setDao(database.getRecipeDao())
                .execute(recipe);
    }
}
