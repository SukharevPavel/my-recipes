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

import ru.sukharev.myrecipes.R;
import ru.sukharev.myrecipes.database.RecipeDatabase;
import ru.sukharev.myrecipes.pojo.Recipe;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(RobolectricTestRunner.class)
public class AddRecipeActivityTest {

    @Mock
    AddRecipePresenter mockPresenter;

    RecipeDatabase database;


    @Before
    public void mockSetup(){
        mockPresenter = Mockito.mock(AddRecipePresenter.class);
    }

    @Before
    public void createDb() {
        Context context = RuntimeEnvironment.application;
        database = Room.inMemoryDatabaseBuilder(context, RecipeDatabase.class).
                allowMainThreadQueries().
                build();
        RecipeDatabase.setDatabase(database);
    }
    @Test
    public void fabClick_addNewRecipe(){
        AddRecipeActivity activity = Robolectric.setupActivity(AddRecipeActivity.class);
        AddRecipeFragment fragment = (AddRecipeFragment) activity.getSupportFragmentManager().findFragmentById(R.id.add_recipe_fragment);
        AddRecipePresenter.init(activity, fragment);
        String title = "testTitle";
        Integer rating = 5;
        String description = "test description which is not very long";
        ((EditText) fragment.getView().findViewById(R.id.add_recipe_title)).setText(title);
        ((EditText) fragment.getView().findViewById(R.id.add_recipe_rating)).setText(String.valueOf(rating));
        ((EditText) fragment.getView().findViewById(R.id.add_recipe_desc)).setText(description);
        activity.findViewById(R.id.fab).performClick();
        List<Recipe> recipes = database.getRecipeDao().getAllRecipes();
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