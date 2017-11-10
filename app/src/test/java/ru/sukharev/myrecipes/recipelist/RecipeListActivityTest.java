package ru.sukharev.myrecipes.recipelist;

import android.content.Intent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowApplication;

import ru.sukharev.myrecipes.R;
import ru.sukharev.myrecipes.addrecipe.AddRecipeActivity;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
public class RecipeListActivityTest {

    @Test
    public void fabClick_openAddRecipeActivity(){
        RecipeListActivity activity = Robolectric.setupActivity(RecipeListActivity.class);
        activity.findViewById(R.id.fab).performClick();

        Intent expectedIntent = new Intent(activity, AddRecipeActivity.class);
        Intent actual = ShadowApplication.getInstance().getNextStartedActivity();
        assertEquals(expectedIntent.getComponent(), actual.getComponent());
    }

}