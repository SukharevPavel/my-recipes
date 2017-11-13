package ru.sukharev.myrecipes.recipelist;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import ru.sukharev.myrecipes.database.RecipeDatabase;
import ru.sukharev.myrecipes.pojo.Recipe;

/**
 * Created by pasha on 07.11.17.
 */

public class RecipeListPresenter implements RecipeListContract.Presenter{

    private static RecipeListPresenter instance;

    private RecipeListContract.View view;

    private RecipeDatabase database;

    private RecipeListPresenter(Context context) {
        database = RecipeDatabase.getInstance(context.getApplicationContext());
    }

    static RecipeListPresenter init(Context context, RecipeListContract.View view){
        if (instance == null) {
            instance = new RecipeListPresenter(context);
        }
        instance.setView(view);
        return instance;
    }


    private void setView(RecipeListContract.View view){
        if (!view.equals(this.view)) {
            instance.view = view;
            instance.view.setPresenter(this);
        }
    }

    @Override
    public void listItemClicked() {

    }

    @Override
    public void start() {
        new GetRecipesList() {
            @Override
            void result(List<Recipe> recipes) {
                view.showList(recipes);
            }
        }.execute();
    }

    private abstract class GetRecipesList extends AsyncTask<Void, Void, List<Recipe>> {


        @Override
        protected List<Recipe> doInBackground(Void... voids) {
            return database.getRecipeDao().getAllRecipes();
        }

        @Override
        protected void onPostExecute(List<Recipe> recipes) {
            super.onPostExecute(recipes);
            result(recipes);

        }

        abstract void result(List<Recipe> recipes);
    }
}
