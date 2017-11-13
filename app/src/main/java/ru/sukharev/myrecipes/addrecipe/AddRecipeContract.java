package ru.sukharev.myrecipes.addrecipe;

import ru.sukharev.myrecipes.base.BasePresenter;
import ru.sukharev.myrecipes.base.BaseView;

/**
 * Created by pasha on 13.11.17.
 */

public interface AddRecipeContract {

    interface View extends BaseView<Presenter>{

    }

    interface Presenter extends BasePresenter<View> {

        void addRecipe(String title, int rating, String desc);

    }
}
