package ru.sukharev.myrecipes.addrecipe;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.design.widget.TextInputLayout;
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
public class AddRecipeRobolectricTest {

    @Mock
    AddRecipePresenter mockPresenter;

    RecipeDatabase database;

    @Before
    public void setup() {
        mockPresenter = Mockito.mock(AddRecipePresenter.class);
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
        String title = "fabClick_addNewRecipe";
        Integer rating = 5;
        String description = "test description which is not very long";
        ((EditText) fragment.getView().findViewById(R.id.add_recipe_title)).setText(title);
        ((EditText) fragment.getView().findViewById(R.id.add_recipe_rating)).setText(String.valueOf(rating));
        ((EditText) fragment.getView().findViewById(R.id.add_recipe_desc)).setText(description);
        activity.findViewById(R.id.fab).performClick();
        List<Recipe> recipes = database.getRecipeDao().getAllRecipes();
        Recipe lastRecipe = recipes.get(recipes.size() - 1);
        assertThat(lastRecipe.title,equalTo(title));
        assertThat(lastRecipe.rating,equalTo(rating));
        assertThat(lastRecipe.description,equalTo(description));
    }

    @Test
    public void fabClick_notifyEmptyTitle(){
        AddRecipeActivity activity = Robolectric.setupActivity(AddRecipeActivity.class);
        AddRecipeFragment fragment = (AddRecipeFragment) activity.getSupportFragmentManager().findFragmentById(R.id.add_recipe_fragment);
        AddRecipePresenter.init(activity, fragment);
        activity.findViewById(R.id.fab).performClick();
        TextInputLayout titleLayout = fragment.getView().findViewById(R.id.add_recipe_title_til);
        assertThat(titleLayout.isErrorEnabled(),equalTo(true));
        assertThat(titleLayout.getError().toString(), equalTo(activity.getString(R.string.add_recipe_title_error)));
    }

    @Test
    public void addRecipe_recipeIsAdded(){
        String title = "addRecipe_recipeIsAdded";
        Integer rating = 5;
        String description = "test description which is not very long";
        Recipe recipe = new Recipe.Builder()
                .setTitle(title)
                .setRating(rating)
                .setDescription(description)
                .build();
        database.getRecipeDao().addRecipe(recipe);
        List<Recipe> recipes = database.getRecipeDao().getAllRecipes();
        Recipe lastRecipe = recipes.get(recipes.size() - 1);
        assertThat(lastRecipe.title,equalTo(title));
        assertThat(lastRecipe.rating,equalTo(rating));
        assertThat(lastRecipe.description,equalTo(description));
    }
}