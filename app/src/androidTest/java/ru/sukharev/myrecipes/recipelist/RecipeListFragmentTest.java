package ru.sukharev.myrecipes.recipelist;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;

import ru.sukharev.myrecipes.R;
import ru.sukharev.myrecipes.database.RecipeDatabase;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.IsNot.not;
import static org.mockito.Mockito.mock;

public class RecipeListFragmentTest {

    @Rule
    public ActivityTestRule<RecipeListActivity> mActivityTestRule =
            new ActivityTestRule<>(RecipeListActivity.class);

    @Mock
    RecipeListFragment fragment;

    RecipeListPresenter presenter;

    @Before
    public void setup(){
        fragment = mock(RecipeListFragment.class);
        presenter = RecipeListPresenter.init(fragment);
    }

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        database = Room.inMemoryDatabaseBuilder(context, RecipeDatabase.class).build();
        recipeDao = database.getRecipeDao();
    }

    @Test
    public void checkIfListHiddenWhenNoItems(){
        onView(withId(R.id.recipe_list_recycler_view)).check(matches(not(isDisplayed())));
    }

}