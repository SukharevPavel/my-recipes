package ru.sukharev.myrecipes.addrecipe;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import ru.sukharev.myrecipes.R;
import ru.sukharev.myrecipes.database.RecipeDatabase;
import ru.sukharev.myrecipes.database.dao.RecipeDao;
import ru.sukharev.myrecipes.pojo.Recipe;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static ru.sukharev.myrecipes.matchers.ReceiptMatchers.hasDrawable;
import static ru.sukharev.myrecipes.matchers.ReceiptMatchers.withViewCount;

@RunWith(AndroidJUnit4.class)
public class AddRecipeAndroidTest {

    @Rule
    public ActivityTestRule<AddRecipeActivity> mActivityTestRule =
            new ActivityTestRule<>(AddRecipeActivity.class);

    RecipeDatabase database;

    RecipeDao recipeDao;

    List<Recipe> recipes;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        database = Room.inMemoryDatabaseBuilder(context, RecipeDatabase.class)
                .allowMainThreadQueries()
                .build();
        RecipeDatabase.setDatabase(database);
        recipeDao = database.getRecipeDao();
    }

    @Test
    public void start_hasFiveRatingStars(){
        Context context = InstrumentationRegistry.getTargetContext();
        Drawable starDrawable = context.getResources().getDrawable(R.drawable.star);
        Drawable emptyStarDrawable = context.getResources().getDrawable(R.drawable.star_empty);
        onView(withId(R.id.add_recipe_rating_holder)).check(matches(withViewCount(hasDrawable(starDrawable, emptyStarDrawable),
                5)));
    }

    @Test
    public void fabClick_addNewRecipe(){
        String title = "testTitle";
        Integer rating = 5;
        String description = "test description which is not very long";
        onView(withId(R.id.add_recipe_title)).perform(typeText(title), closeSoftKeyboard());
        onView(withId(R.id.add_recipe_rating)).perform(typeText(String.valueOf(rating)), closeSoftKeyboard());
        onView(withId(R.id.add_recipe_desc)).perform(typeText(description), closeSoftKeyboard());
        onView(withId(R.id.fab)).perform(click());
        recipes = database.getRecipeDao().getAllRecipes();
        assertThat(recipes.size(), equalTo(1));
        Recipe recipe = recipes.get(0);
        assertThat(recipe.title,equalTo(title));
        assertThat(recipe.rating,equalTo(rating));
        assertThat(recipe.description,equalTo(description));
    }

}