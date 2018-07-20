package ru.sukharev.myrecipes.recipelist

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.ButterKnife
import kotlinx.android.synthetic.main.fragment_recipe_list.*
import ru.sukharev.myrecipes.R
import ru.sukharev.myrecipes.pojo.Recipe


class RecipeListFragment : Fragment(), RecipeListContract.View {

    private lateinit var presenter: RecipeListContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(RecipeListFragment::class.java.name, "onCreate")

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_recipe_list, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onStart() {
        super.onStart()
        presenter.start()
    }

    override fun showList(list: List<Recipe>) {
        if (list.isEmpty()) {
            recipe_list_no_items.visibility = View.VISIBLE
            recipe_list_recycler_view.visibility = View.GONE
        } else {
            recipe_list_no_items.visibility = View.GONE
            recipe_list_recycler_view.visibility = View.VISIBLE
            setListItems(list)
        }
    }

    private fun setListItems(list: List<Recipe>) {
        recipe_list_recycler_view.layoutManager = LinearLayoutManager(context)
        recipe_list_recycler_view.adapter = RecipeListAdapter(list)
    }

    override fun setPresenter(presenter: RecipeListContract.Presenter) {
        this.presenter = presenter
    }

    class RecipeListAdapter(private val recipes: List<Recipe>) : RecyclerView.Adapter<RecipeListAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false))
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val recipe = recipes[position]
            holder.title.text = recipe.title
        }

        override fun getItemCount(): Int {
            return recipes.size
        }

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            internal var title: TextView = itemView.findViewById(android.R.id.text1)

        }
    }


}
