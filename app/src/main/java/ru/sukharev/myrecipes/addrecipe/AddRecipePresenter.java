package ru.sukharev.myrecipes.addrecipe;

import android.content.Context;

import ru.sukharev.myrecipes.database.RecipeDatabase;
import ru.sukharev.myrecipes.database.dao.RecipeDao;
import ru.sukharev.myrecipes.pojo.Recipe;

/**
 * Presenter for add recipe view, using for adding new recipes with data which we got from add recipe view
 */
public class AddRecipePresenter implements AddRecipeContract.Presenter {

    private static AddRecipePresenter instance;

    private AddRecipeContract.View view;
    private RecipeDatabase database;

    private AddRecipePresenter(Context context) {
        database = RecipeDatabase.getInstance(context);
    }

    static AddRecipePresenter init(Context context, AddRecipeContract.View view){
        if (instance == null) {
            instance = new AddRecipePresenter(context.getApplicationContext());
        }
        instance.setView(view);
        return instance;
    }

    private void setView(AddRecipeContract.View view) {
        if (!view.equals(this.view)) {
            this.view = view;
            view.setPresenter(this);
        }
    }

    @Override
    public void start() {

    }

    @Override
    public void addRecipe(String title, int rating, String desc) {
        final Recipe recipe = new Recipe.Builder()
                .setTitle(title)
                .setRating(rating)
                .setDescription(desc)
                .build();
        new RecipeDao.AppRecipeAsyncTask() {
            @Override
            public void result(long result) {
                if (result >= 0) {
                    view.onRecipeAdded();
                } else {
                    view.onRecipeAddingError();
                }
            }
        }
        .setDao(database.getRecipeDao())
        .execute(recipe);
    }


}
