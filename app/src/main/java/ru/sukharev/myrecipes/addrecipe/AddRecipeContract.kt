package ru.sukharev.myrecipes.addrecipe

import ru.sukharev.myrecipes.base.BasePresenter
import ru.sukharev.myrecipes.base.BaseView

/**
 * Created by pasha on 13.11.17.
 */

interface AddRecipeContract {

    interface View : BaseView<Presenter> {

        fun onRecipeAdded()

        fun onRecipeAddingError()

        fun setRating(rating: Int)
    }

    interface Presenter : BasePresenter<View> {

        fun addRecipe(title: String, desc: String)

        fun setRating(rating: Int)
    }
}
