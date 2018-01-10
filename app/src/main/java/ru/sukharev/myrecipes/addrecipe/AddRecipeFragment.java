package ru.sukharev.myrecipes.addrecipe;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.sukharev.myrecipes.R;


/**
 * A placeholder fragment containing a simple view.
 */
public class AddRecipeFragment extends Fragment implements AddRecipeContract.View, AddRecipeActivity.Listener{

    private AddRecipeContract.Presenter presenter;
    private final static int MAX_RATING = 5;

    public AddRecipeFragment() {
    }

    @BindView(R.id.add_recipe_title)
    EditText titleEdit;
    @BindView(R.id.add_recipe_desc)
    EditText descEdit;
    @BindView(R.id.add_recipe_title_til)
    TextInputLayout titleTil;
    @BindView(R.id.add_recipe_rating_holder)
    ViewGroup ratingHolder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_recipe, container, false);
        ButterKnife.bind(this, view);
        setUpStars();
        return view;
    }

    private void setUpStars() {
        for (int i = 0 ; i < MAX_RATING; i++) {
            addStarToRatingHolder();
        }
    }

    private View makeEmptyStar() {
        ImageView starView = new ImageView(getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.weight = 1f;
        starView.setLayoutParams(params);
        starView.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.star_empty));
        return starView;
    }

    private void addStarToRatingHolder() {
        View star = makeEmptyStar();
        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rating = ratingHolder.indexOfChild(v);
                presenter.setRating(rating);
                setRating(rating);
            }
        });
        ratingHolder.addView(star);
    }

    @Override
    public void setRating(int rating) {
        fillStarsAccordingToRating(rating);
    }

    private void fillStarsAccordingToRating(int rating) {
        for (int childIndex = 0; childIndex < ratingHolder.getChildCount(); childIndex++ ){
            ImageView star = (ImageView) ratingHolder.getChildAt(childIndex);
            if (rating >= childIndex) {
                star.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.star));
            } else {
                star.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.star_empty));
            }
        }
    }


    @Override
    public void onFabClick() {
        String title = titleEdit.getText().toString();
        String desc = descEdit.getText().toString();
        if (!title.isEmpty()) {
            titleTil.setErrorEnabled(false);
            presenter.addRecipe(title, desc);
        } else {
            titleTil.setErrorEnabled(true);
            titleTil.setError(getString(R.string.add_recipe_title_error));
        }
    }

    @Override
    public void setPresenter(AddRecipeContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onRecipeAdded() {
        getActivity().finish();
    }

    @Override
    public void onRecipeAddingError() {

    }
}
