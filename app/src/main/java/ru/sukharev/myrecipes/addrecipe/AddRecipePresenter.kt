package ru.sukharev.myrecipes.addrecipe

import android.content.Context
import ru.sukharev.myrecipes.model.IModel
import ru.sukharev.myrecipes.model.RecipeRepository
import ru.sukharev.myrecipes.pojo.Recipe

/**
 * Presenter for add recipe view, using for adding new recipes with data which we got from add recipe view
 */
class AddRecipePresenter private constructor(context: Context) : AddRecipeContract.Presenter {

    private lateinit var view: AddRecipeContract.View
    private val recipeModel: IModel

    private var currentRating = 0

    init {
        recipeModel = RecipeRepository.getInstance(context)
    }

    private fun setView(view: AddRecipeContract.View) {
        if (view != this.view) {
            this.view = view
            view.setPresenter(this)
        }
    }

    override fun start() {
        view.setRating(currentRating)
    }

    override fun addRecipe(title: String, desc: String) {
        val recipe = Recipe.Builder()
                .setTitle(title)
                .setRating(currentRating)
                .setDescription(desc)
                .build()
        recipeModel.addRecipe(recipe, object : IModel.AddRecipeCallback {
            override fun onSuccess(result: Long) {
                view.onRecipeAdded()
            }

            override fun onFail() {
                view.onRecipeAddingError()
            }
        })
    }

    override fun setRating(rating: Int) {
        currentRating = rating
        view.setRating(currentRating)
    }

    companion object {

        private var instance: AddRecipePresenter? = null

        internal fun init(context: Context, view: AddRecipeContract.View){
            if (instance == null) {
                instance = AddRecipePresenter(context);
            }
            instance!!.setView(view)
        }
    }


}
