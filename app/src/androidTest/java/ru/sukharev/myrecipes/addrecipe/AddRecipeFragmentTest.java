package ru.sukharev.myrecipes.addrecipe;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import ru.sukharev.myrecipes.R;
import ru.sukharev.myrecipes.database.RecipeDatabase;
import ru.sukharev.myrecipes.database.dao.RecipeDao;
import ru.sukharev.myrecipes.pojo.Recipe;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.fail;

/**
 * Created by pasha on 13.11.17.
 */
public class AddRecipeFragmentTest {

    @Rule
    public ActivityTestRule<AddRecipeActivity> mActivityTestRule =
            new ActivityTestRule<>(AddRecipeActivity.class);

    RecipeDatabase database;

    RecipeDao recipeDao;

    List<Recipe> recipes;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        database = Room.inMemoryDatabaseBuilder(context, RecipeDatabase.class).build();
        recipeDao = database.getRecipeDao();
    }

    @Test
    @UiThreadTest
    public void fabClick_addNewRecipe(){
        AppCompatActivity activity = mActivityTestRule.getActivity();
        AddRecipeFragment fragment = (AddRecipeFragment) activity.getSupportFragmentManager().findFragmentById(R.id.add_recipe_fragment);
        AddRecipePresenter.init(activity, fragment);
        RecipeDatabase.setDatabase(database);
        String title = "testTitle";
        Integer rating = 5;
        String description = "test description which is not very long";
        ((EditText) fragment.getView().findViewById(R.id.add_recipe_title)).setText(title);
        ((EditText) fragment.getView().findViewById(R.id.add_recipe_rating)).setText(String.valueOf(rating));
        ((EditText) fragment.getView().findViewById(R.id.add_recipe_desc)).setText(description);
        activity.findViewById(R.id.fab).performClick();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            fail("Interrupted");
        }
        final CountDownLatch latch = new CountDownLatch(1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    recipes = database.getRecipeDao().getAllRecipes();
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    latch.countDown();
                }
            }
        }).start();
        try {
            latch.await();
        } catch (InterruptedException e) {
            fail("Interrupted");
        }
        assertThat(recipes.size(), equalTo(1));
        Recipe recipe = recipes.get(0);
        assertThat(recipes.get(0).title,equalTo(title));
        assertThat(recipes.get(0).rating,equalTo(rating));
        assertThat(recipes.get(0).description,equalTo(description));
    }

}