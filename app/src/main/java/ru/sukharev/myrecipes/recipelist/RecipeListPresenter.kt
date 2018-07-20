package ru.sukharev.myrecipes.recipelist

import android.content.Context

import ru.sukharev.myrecipes.model.IModel
import ru.sukharev.myrecipes.model.RecipeRepository
import ru.sukharev.myrecipes.pojo.Recipe

/**
 * Presenter for view which shows list of all recipes
 */

class RecipeListPresenter private constructor(context: Context) : RecipeListContract.Presenter {

    private var view: RecipeListContract.View? = null

    private val recipeModel: IModel

    init {
        recipeModel = RecipeRepository.getInstance(context.applicationContext)
    }


    private fun setView(view: RecipeListContract.View) {
        if (view != this.view) {
            instance.view = view
            instance.view!!.setPresenter(this)
        }
    }

    override fun listItemClicked() {

    }

    override fun start() {
        recipeModel.getRecipes(object : IModel.GetRecipesCallback {
            override fun onResult(recipes: List<Recipe>) {
                view!!.showList(recipes)
            }
        })
    }

    companion object {

        private lateinit var instance: RecipeListPresenter

        internal fun init(context: Context, view: RecipeListContract.View): RecipeListPresenter {
            if (instance == null) {
                instance = RecipeListPresenter(context)
            }
            instance.setView(view)
            return instance
        }
    }


}
