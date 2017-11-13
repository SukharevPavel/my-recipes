package ru.sukharev.myrecipes.addrecipe;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.sukharev.myrecipes.R;


/**
 * A placeholder fragment containing a simple view.
 */
public class AddRecipeFragment extends Fragment implements AddRecipeContract.View, AddRecipeActivity.Listener{

    private AddRecipeContract.Presenter presenter;

    public AddRecipeFragment() {
    }

    @BindView(R.id.add_recipe_title)
    EditText titleEdit;
    @BindView(R.id.add_recipe_rating)
    EditText ratingEdit;
    @BindView(R.id.add_recipe_desc)
    EditText descEdit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_recipe, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onFabClick() {
        String title = titleEdit.getText().toString();
        int rating = Integer.valueOf(ratingEdit.getText().toString());
        String desc = descEdit.getText().toString();
        presenter.addRecipe(title, rating, desc);
    }

    @Override
    public void setPresenter(AddRecipeContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
