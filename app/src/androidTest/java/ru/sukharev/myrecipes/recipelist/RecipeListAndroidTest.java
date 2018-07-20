package ru.sukharev.myrecipes.recipelist;

import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import ru.sukharev.myrecipes.R;
import ru.sukharev.myrecipes.addrecipe.AddRecipeActivity;
import ru.sukharev.myrecipes.database.RecipeDatabase;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.mockito.Mockito.mock;

@RunWith(AndroidJUnit4.class)
public class RecipeListAndroidTest {

    @Rule
    public IntentsTestRule<RecipeListActivity> intentsRule =
            new IntentsTestRule<>(RecipeListActivity.class);

    @Mock
    RecipeListFragment fragment;

    RecipeListPresenter presenter;

    RecipeDatabase database;

    @Before
    public void setup(){
        database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(), RecipeDatabase.class)
                .allowMainThreadQueries()
                .build();
        RecipeDatabase.Companion.setDatabase(database);
        fragment = mock(RecipeListFragment.class);
        presenter = RecipeListPresenter.Companion.init(InstrumentationRegistry.getContext(), fragment);
    }

    @Test
    public void fabClick_openAddRecipeActivity(){
        onView(withId(R.id.fab)).perform(click());
        intended(hasComponent(AddRecipeActivity.class.getName()));
    }



}