package ru.sukharev.myrecipes.addrecipe

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_recipe.*
import ru.sukharev.myrecipes.R


class AddRecipeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_recipe)
        setSupportActionBar(toolbar)
        val fragment = supportFragmentManager.findFragmentById(R.id.add_recipe_fragment) as AddRecipeFragment
        AddRecipePresenter.init(this, fragment)
        fab.setOnClickListener {
            try {
                val listener = this@AddRecipeActivity.supportFragmentManager.findFragmentById(R.id.add_recipe_fragment) as Listener
                listener.onFabClick()
            } catch (ex: ClassCastException) {
                throw AssertionError("inner fragment must implements activity listener")
            }
        }
    }

    //used for passing activity events to fragments
    interface Listener {

        fun onFabClick()

    }

}
