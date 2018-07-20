package ru.sukharev.myrecipes.addrecipe

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import butterknife.ButterKnife
import kotlinx.android.synthetic.main.fragment_add_recipe.*
import ru.sukharev.myrecipes.R


/**
 * A placeholder fragment containing a simple view.
 */
class AddRecipeFragment : Fragment(), AddRecipeContract.View, AddRecipeActivity.Listener {

    private lateinit var presenter: AddRecipeContract.Presenter



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_add_recipe, container, false)
        ButterKnife.bind(this, view)
        setUpStars()
        return view
    }

    private fun setUpStars() {
        for (i in 0 until MAX_RATING) {
            addStarToRatingHolder()
        }
    }

    private fun makeEmptyStar(): View {
        val starView = ImageView(context)
        val params = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
        params.weight = 1f
        starView.layoutParams = params
        starView.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.star_empty))
        return starView
    }

    private fun addStarToRatingHolder() {
        val star = makeEmptyStar()
        star.setOnClickListener { v ->
            val rating = add_recipe_rating_holder.indexOfChild(v)
            presenter.setRating(rating)
            setRating(rating)
        }
        add_recipe_rating_holder.addView(star)
    }

    override fun setRating(rating: Int) {
        fillStarsAccordingToRating(rating)
    }

    private fun fillStarsAccordingToRating(rating: Int) {
        for (childIndex in 0 until add_recipe_rating_holder.childCount) {
            val star = add_recipe_rating_holder.getChildAt(childIndex) as ImageView
            if (rating >= childIndex) {
                star.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.star))
            } else {
                star.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.star_empty))
            }
        }
    }


    override fun onFabClick() {
        val title = add_recipe_title.text.toString()
        val desc = add_recipe_desc.text.toString()
        if (!title.isEmpty()) {
            add_recipe_title_til.isErrorEnabled = false
            presenter.addRecipe(title, desc)
        } else {
            add_recipe_title_til.isErrorEnabled = true
            add_recipe_title_til.error = getString(R.string.add_recipe_title_error)
        }
    }

    override fun setPresenter(presenter: AddRecipeContract.Presenter) {
        this.presenter = presenter
    }

    override fun onRecipeAdded() {
        activity!!.finish()
    }

    override fun onRecipeAddingError() {

    }

    companion object {
        private const val MAX_RATING = 5
    }
}
