package ru.sukharev.myrecipes.recipelist

import ru.sukharev.myrecipes.base.BasePresenter
import ru.sukharev.myrecipes.base.BaseView
import ru.sukharev.myrecipes.pojo.Recipe

/**
 * Created by pasha on 07.11.17.
 */

interface RecipeListContract {

    interface View : BaseView<Presenter> {

        fun showList(list: List<Recipe>)

    }

    interface Presenter : BasePresenter<View> {

        fun listItemClicked()

    }
}
