package ru.sukharev.myrecipes.model;

import java.util.List;

import ru.sukharev.myrecipes.pojo.Recipe;

/**
 * Interface describing user interaction with model layer
 */

public interface IModel {

    void getRecipes(GetRecipesCallback callback);

    void addRecipe(Recipe recipe, AddRecipeCallback callback);

    interface GetRecipesCallback {

        void onResult(List<Recipe> recipes);
    }

    interface AddRecipeCallback{

        void onSuccess(long result);

        void onFail();
    }
}
