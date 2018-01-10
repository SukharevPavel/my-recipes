package ru.sukharev.myrecipes.addrecipe;

import ru.sukharev.myrecipes.base.BasePresenter;
import ru.sukharev.myrecipes.base.BaseView;

/**
 * Created by pasha on 13.11.17.
 */

public interface AddRecipeContract {

    interface View extends BaseView<Presenter>{

        void onRecipeAdded();

        void onRecipeAddingError();

        void setRating(int rating);
    }

    interface Presenter extends BasePresenter<View> {

        void addRecipe(String title, String desc);

        void setRating(int rating);
    }
}
