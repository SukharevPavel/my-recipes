package ru.sukharev.myrecipes.addrecipe;

import android.app.Fragment;
import android.widget.TextView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import ru.sukharev.myrecipes.R;

@RunWith(RobolectricTestRunner.class)
public class AddRecipeActivityTest {

    @Test
    public void fabClick_addNewRecipe(){
        AddRecipeActivity activity = Robolectric.setupActivity(AddRecipeActivity.class);
        Fragment fragment = activity.getSupportFragmentManager().findFragmentById(R.id.add_recipe_fragment);
        String title = ((TextView) fragment.getView().findViewById(R.id.add_recipe_title)).getText();
        String desc = ((TextView))
        activity.findViewById(R.id.fab).performClick();

    }

}