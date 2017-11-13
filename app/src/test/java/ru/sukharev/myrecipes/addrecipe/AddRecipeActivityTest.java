package ru.sukharev.myrecipes.addrecipe;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.widget.EditText;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import ru.sukharev.myrecipes.R;
import ru.sukharev.myrecipes.database.RecipeDatabase;
import ru.sukharev.myrecipes.database.dao.RecipeDao;
import ru.sukharev.myrecipes.pojo.Recipe;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.fail;

@RunWith(RobolectricTestRunner.class)
public class AddRecipeActivityTest {

    @Mock
    AddRecipePresenter mockPresenter;

    RecipeDatabase database;

    RecipeDao recipeDao;

    List<Recipe> recipes;

    @Before
    public void mockSetup(){
        mockPresenter = Mockito.mock(AddRecipePresenter.class);
    }

    @Before
    public void createDb() {
        Context context = RuntimeEnvironment.application;
        database = Room.inMemoryDatabaseBuilder(context, RecipeDatabase.class).build();
    }
    @Test
    public void fabClick_addNewRecipe(){
        AddRecipeActivity activity = Robolectric.setupActivity(AddRecipeActivity.class);
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
        final CountDownLatch latch = new CountDownLatch(1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    recipes = database.getRecipeDao().getAllRecipes();

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

   /* @Test
    public void fabClick_presenterIsCalled(){
        AddRecipeActivity activity = Robolectric.setupActivity(AddRecipeActivity.class);
        AddRecipeFragment fragment = (AddRecipeFragment) activity.getSupportFragmentManager().findFragmentById(R.id.add_recipe_fragment);
        fragment.setPresenter(mockPresenter);
        String title = "testTitle";
        Integer rating = 5;
        String description = "test description which is not very long";
        ((EditText) fragment.getView().findViewById(R.id.add_recipe_title)).setText(title);
        ((EditText) fragment.getView().findViewById(R.id.add_recipe_rating)).setText(String.valueOf(rating));
        ((EditText) fragment.getView().findViewById(R.id.add_recipe_desc)).setText(description);
        activity.findViewById(R.id.fab).performClick();
        verify(mockPresenter).addRecipe(title, rating, description);
    }*/



}