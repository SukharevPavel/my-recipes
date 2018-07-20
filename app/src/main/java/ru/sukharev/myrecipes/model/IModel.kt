package ru.sukharev.myrecipes.model

import ru.sukharev.myrecipes.pojo.Recipe

/**
 * Interface describing user interaction with model layer
 */

interface IModel {

    fun getRecipes(callback: GetRecipesCallback)

    fun addRecipe(recipe: Recipe, callback: AddRecipeCallback)

    interface GetRecipesCallback {

        fun onResult(recipes: List<Recipe>)
    }

    interface AddRecipeCallback {

        fun onSuccess(result: Long)

        fun onFail()
    }
}
