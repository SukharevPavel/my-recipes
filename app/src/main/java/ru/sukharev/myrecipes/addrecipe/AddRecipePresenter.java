package ru.sukharev.myrecipes.addrecipe;

import android.content.Context;

import ru.sukharev.myrecipes.model.IModel;
import ru.sukharev.myrecipes.model.RecipeRepository;
import ru.sukharev.myrecipes.pojo.Recipe;

/**
 * Presenter for add recipe view, using for adding new recipes with data which we got from add recipe view
 */
public class AddRecipePresenter implements AddRecipeContract.Presenter {

    private static AddRecipePresenter instance;

    private AddRecipeContract.View view;
    private IModel recipeModel;

    private AddRecipePresenter(Context context) {
        recipeModel = RecipeRepository.getInstance(context);
    }

    static void init(Context context, AddRecipeContract.View view){
        if (instance == null) {
            instance = new AddRecipePresenter(context.getApplicationContext());
        }
        instance.setView(view);
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
        recipeModel.addRecipe(recipe, new IModel.AddRecipeCallback() {
            @Override
            public void onSuccess(long result) {
                view.onRecipeAdded();
            }

            @Override
            public void onFail() {
                view.onRecipeAddingError();
            }
        });
    }


}
