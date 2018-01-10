package ru.sukharev.myrecipes.recipelist;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.shadows.ShadowApplication;

import java.util.List;

import ru.sukharev.myrecipes.R;
import ru.sukharev.myrecipes.addrecipe.AddRecipeActivity;
import ru.sukharev.myrecipes.database.RecipeDatabase;
import ru.sukharev.myrecipes.database.dao.RecipeDao;
import ru.sukharev.myrecipes.pojo.Recipe;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricTestRunner.class)
public class RecipeListRobolectricTest {

    @Mock
    RecipeListFragment fragment;

    RecipeListPresenter presenter;

    RecipeDatabase database;

    RecipeDao recipeDao;

    List<Recipe> recipes;

    @Before
    public void setup(){
        Context context = RuntimeEnvironment.application;
        database = Room.inMemoryDatabaseBuilder(context, RecipeDatabase.class)
                .allowMainThreadQueries()
                .build();
        RecipeDatabase.setDatabase(database);
        fragment = mock(RecipeListFragment.class);
        presenter = RecipeListPresenter.init(context, fragment);
        recipeDao = database.getRecipeDao();
    }

    @Test
    public void start_showList(){
        presenter.start();
        recipes = recipeDao.getAllRecipes();
        verify(fragment).showList(recipes);
        assertThat(1, equalTo(recipes.size()));
    }

    @Test
    public void addRecipe_updateDatabase(){
        final Recipe recipe = new Recipe.Builder()
                .setTitle("title")
                .setRating(5)
                .setDescription("description")
                .build();
        recipeDao.addRecipe(recipe);
        recipes = recipeDao.getAllRecipes();
        assertThat(recipes.size(), equalTo(1));
        assertThat(recipes.get(0), equalTo(recipe));
    }

    @Test
    public void fabClick_openAddRecipeActivity(){
        RecipeListActivity activity = Robolectric.setupActivity(RecipeListActivity.class);
        activity.findViewById(R.id.fab).performClick();

        Intent expectedIntent = new Intent(activity, AddRecipeActivity.class);
        Intent actual = ShadowApplication.getInstance().getNextStartedActivity();
        assertEquals(expectedIntent.getComponent(), actual.getComponent());
    }

}