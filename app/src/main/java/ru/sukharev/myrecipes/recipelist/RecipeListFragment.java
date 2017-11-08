package ru.sukharev.myrecipes.recipelist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.sukharev.myrecipes.R;
import ru.sukharev.myrecipes.pojo.Recipe;


public class RecipeListFragment extends Fragment implements RecipeListContract.View{

    @BindView(R.id.recipe_list_recycler_view)
    RecyclerView recipeRecyclerView;
    @BindView(R.id.recipe_list_no_items)
    TextView noItemsPlaceholderView;

    private RecipeListContract.Presenter presenter;

    public RecipeListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(RecipeListFragment.class.getName(), "onCreate");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.start();
    }

    @Override
    public void showList(List<Recipe> list) {
        if (list.isEmpty()) {
            noItemsPlaceholderView.setVisibility(View.VISIBLE);
            recipeRecyclerView.setVisibility(View.GONE);
        } else {
            noItemsPlaceholderView.setVisibility(View.GONE);
            recipeRecyclerView.setVisibility(View.VISIBLE);
            setListItems(list);
        }
    }

    private void setListItems(List<Recipe> list) {
        recipeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recipeRecyclerView.setAdapter(new recipeListAdapter(list));
    }

    @Override
    public void setPresenter(RecipeListContract.Presenter presenter) {
        this.presenter = presenter;
    }

    public static class recipeListAdapter extends RecyclerView.Adapter<recipeListAdapter.ViewHolder> {

        private List<Recipe> recipes;

        public recipeListAdapter(List<Recipe> recipes) {
            this.recipes = recipes;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).
                    inflate(android.R.layout.simple_list_item_1, parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
                Recipe recipe = new Recipe();
                holder.title.setText(recipe.title);
        }

        @Override
        public int getItemCount() {
            return recipes.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder{

            TextView title;

            public ViewHolder(View itemView) {
                super(itemView);
                title = itemView.findViewById(android.R.id.text1);
            }
        }
    }


}
