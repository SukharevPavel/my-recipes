package ru.sukharev.myrecipes.recipelist;

import android.content.Context;

import java.util.List;

import ru.sukharev.myrecipes.model.IModel;
import ru.sukharev.myrecipes.model.RecipeRepository;
import ru.sukharev.myrecipes.pojo.Recipe;

/**
 * Presenter for view which shows list of all recipes
 */

public class RecipeListPresenter implements RecipeListContract.Presenter{

    private static RecipeListPresenter instance;

    private RecipeListContract.View view;

    private IModel recipeModel;

    private RecipeListPresenter(Context context) {
        recipeModel = RecipeRepository.getInstance(context.getApplicationContext());
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
        recipeModel.getRecipes(new IModel.GetRecipesCallback() {
            @Override
            public void onResult(List<Recipe> recipes) {
                view.showList(recipes);
            }
        });
    }


}
