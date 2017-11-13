package ru.sukharev.myrecipes.recipelist;

import android.arch.persistence.room.Room;
import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import ru.sukharev.myrecipes.database.RecipeDatabase;
import ru.sukharev.myrecipes.database.dao.RecipeDao;
import ru.sukharev.myrecipes.pojo.Recipe;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricTestRunner.class)
public class RecipeListPresenterTest {

    @Mock
    RecipeListFragment fragment;

    RecipeListPresenter presenter;

    RecipeDatabase database;

    RecipeDao recipeDao;

    List<Recipe> recipes;

    @Before
    public void setup(){
        Context context = RuntimeEnvironment.application;
        database = Room.inMemoryDatabaseBuilder(context, RecipeDatabase.class).build();
        fragment = mock(RecipeListFragment.class);
        presenter = RecipeListPresenter.init(context, fragment);
        recipeDao = database.getRecipeDao();
    }

    @Test
    public void start_showList(){
        presenter.start();
        final CountDownLatch latch = new CountDownLatch(1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    recipes = recipeDao.getAllRecipes();
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
        verify(fragment).showList(recipes);
        assertThat(2, equalTo(recipes.size()));
    }

    @Test
    public void addRecipe_updateDatabase(){
        final Recipe recipe = new Recipe.Builder()
                .setTitle("title")
                .setRating(5)
                .setDescription("description")
                .build();
        final CountDownLatch latch = new CountDownLatch(1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    recipeDao.addRecipe(recipe);
                    recipes = recipeDao.getAllRecipes();

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
        assertThat(recipes.get(0), equalTo(recipe));

    }

}