package ru.sukharev.myrecipes.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.os.AsyncTask

import ru.sukharev.myrecipes.pojo.Recipe

/**
 * DAO for interacting with Recipe table
 */

@Dao
interface RecipeDao {

    @get:Query("Select * from Recipe")
    val allRecipes: List<Recipe>

    @Insert
    fun addRecipe(recipe: Recipe): Long

    abstract class AddRecipeAsyncTask : AsyncTask<Recipe, Void, Long>() {

        private lateinit var dao: RecipeDao

        fun setDao(dao: RecipeDao): AddRecipeAsyncTask {
            this.dao = dao
            return this
        }

        override fun doInBackground(vararg recipes: Recipe): Long? {
            return dao.addRecipe(recipes[0])
        }

        override fun onPostExecute(result: Long?) {
            super.onPostExecute(result)
            result(result!!)
        }

        abstract fun result(result: Long)
    }

    abstract class GetRecipesListTask : AsyncTask<Void, Void, List<Recipe>>() {

        private var dao: RecipeDao? = null

        fun setDao(dao: RecipeDao): GetRecipesListTask {
            this.dao = dao
            return this
        }

        override fun doInBackground(vararg voids: Void): List<Recipe> {
            return dao!!.allRecipes
        }

        override fun onPostExecute(recipes: List<Recipe>) {
            super.onPostExecute(recipes)
            result(recipes)

        }

        abstract fun result(recipes: List<Recipe>)
    }


}
