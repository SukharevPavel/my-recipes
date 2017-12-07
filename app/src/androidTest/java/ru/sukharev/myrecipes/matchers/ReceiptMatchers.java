package ru.sukharev.myrecipes.matchers;

import android.graphics.drawable.Drawable;
import android.support.test.espresso.core.deps.guava.base.Predicate;
import android.support.test.espresso.core.deps.guava.collect.Iterables;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.espresso.util.TreeIterables;
import android.view.View;
import android.widget.ImageView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;


/**
 * Class for custom matchers for testing
 */

public class ReceiptMatchers {

    public static Matcher<View> withViewCount(final Matcher<View> viewMatcher, final int expectedCount) {
        return new TypeSafeMatcher<View>() {
            int actualCount = -1;

            @Override
            public void describeTo(Description description) {
                if (actualCount >= 0) {
                    description.appendText("With expected number of items: " + expectedCount);
                    description.appendText("\n With matcher: ");
                    viewMatcher.describeTo(description);
                    description.appendText("\n But got: " + actualCount);
                }
            }

            @Override
            protected boolean matchesSafely(View root) {
                actualCount = 0;
                Iterable<View> iterable = TreeIterables.breadthFirstViewTraversal(root);
                actualCount = Iterables.size(Iterables.filter(iterable, withMatcherPredicate(viewMatcher)));
                return actualCount == expectedCount;
            }
        };
    }

    private static Predicate<View> withMatcherPredicate(final Matcher<View> matcher) {
        return new Predicate<View>() {
            @Override
            public boolean apply(@javax.annotation.Nullable View view) {
                return matcher.matches(view);
            }

        };
    }

    public static BoundedMatcher<View, ImageView> hasDrawable(final Drawable ... drawables) {
        return new BoundedMatcher<View, ImageView>(ImageView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("has drawable");
            }

            @Override
            public boolean matchesSafely(ImageView imageView) {
                for (Drawable drawable : drawables) {
                    if (imageView.getDrawable().equals(drawable)) {
                        return true;
                    }
                }
                return false;
            }
        };
    }
}
