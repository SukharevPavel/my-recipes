package ru.sukharev.myrecipes.addingredient;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.sukharev.myrecipes.R;


/**
 * A placeholder fragment containing a simple view.
 */
public class AddIngredientActivityFragment extends Fragment {

    public AddIngredientActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_ingredient, container, false);
    }
}
