package ru.sukharev.myrecipes.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.os.AsyncTask;

import java.util.List;

import ru.sukharev.myrecipes.pojo.Recipe;

/**
 * DAO for interacting with Recipe table
 */

@Dao
public interface RecipeDao {

    @Insert
    long addRecipe(Recipe recipe);

    @Query("Select * from Recipe")
    List<Recipe> getAllRecipes();

    abstract class AddRecipeAsyncTask extends AsyncTask<Recipe, Void, Long> {

        private RecipeDao dao;

        public AddRecipeAsyncTask setDao(RecipeDao dao) {
            this.dao = dao;
            return this;
        }

        @Override
        protected Long doInBackground(Recipe... recipes) {
            return dao.addRecipe(recipes[0]);
        }

        @Override
        protected void onPostExecute(Long result) {
            super.onPostExecute(result);
            result(result);
        }

        public abstract void result(long result);
    }

    abstract class GetRecipesListTask extends AsyncTask<Void, Void, List<Recipe>> {

        private RecipeDao dao;

        public GetRecipesListTask setDao(RecipeDao dao) {
            this.dao = dao;
            return this;
        }

        @Override
        protected List<Recipe> doInBackground(Void... voids) {
            return dao.getAllRecipes();
        }

        @Override
        protected void onPostExecute(List<Recipe> recipes) {
            super.onPostExecute(recipes);
            result(recipes);

        }

        public abstract void result(List<Recipe> recipes);
    }


}
