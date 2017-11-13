package ru.sukharev.myrecipes.recipelist;

import java.util.ArrayList;

import ru.sukharev.myrecipes.pojo.Recipe;

/**
 * Created by pasha on 07.11.17.
 */

public class RecipeListPresenter implements RecipeListContract.Presenter{

    private static RecipeListPresenter instance;

    private RecipeListContract.View view;

    private RecipeListPresenter() {
    }

    static RecipeListPresenter init(RecipeListContract.View view){
        if (instance == null) {
            instance = new RecipeListPresenter();
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
        view.showList(new ArrayList<Recipe>());
    }
}
