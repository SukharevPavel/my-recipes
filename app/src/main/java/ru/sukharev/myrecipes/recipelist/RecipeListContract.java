package ru.sukharev.myrecipes.recipelist;

import java.util.List;

import ru.sukharev.myrecipes.base.BasePresenter;
import ru.sukharev.myrecipes.base.BaseView;
import ru.sukharev.myrecipes.pojo.Recipe;

/**
 * Created by pasha on 07.11.17.
 */

public interface RecipeListContract {

    interface View extends BaseView<Presenter> {

        void showList(List<Recipe> list);

    }

    interface Presenter extends BasePresenter<View> {

        void listItemClicked();

    }
}
