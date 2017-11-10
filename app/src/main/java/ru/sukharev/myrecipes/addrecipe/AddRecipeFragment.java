package ru.sukharev.myrecipes.addrecipe;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.sukharev.myrecipes.R;


/**
 * A placeholder fragment containing a simple view.
 */
public class AddRecipeFragment extends Fragment {

    public AddRecipeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_recipe, container, false);
    }
}
