package ru.sukharev.myrecipes.recipelist

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem

import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import ru.sukharev.myrecipes.R
import ru.sukharev.myrecipes.addrecipe.AddRecipeActivity

class RecipeListActivity : AppCompatActivity() {

    @BindView(R.id.fab)
    internal var floatingActionButton: FloatingActionButton? = null
    @BindView(R.id.toolbar)
    internal var toolbar: Toolbar? = null

    @OnClick(R.id.fab)
    fun openAddRecipeActivity() {
        val intent = Intent(this@RecipeListActivity, AddRecipeActivity::class.java)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)
        ButterKnife.bind(this)
        setSupportActionBar(toolbar)
        val fragment = supportFragmentManager.findFragmentById(R.id.recipe_list_fragment) as RecipeListFragment
        RecipeListPresenter.init(this, fragment)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_start, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)

    }
}
