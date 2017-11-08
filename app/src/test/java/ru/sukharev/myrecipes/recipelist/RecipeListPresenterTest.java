package ru.sukharev.myrecipes.recipelist;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Created by pasha on 07.11.17.
 */
public class RecipeListPresenterTest {

    @Mock
    RecipeListFragment fragment;

    RecipeListPresenter presenter;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        presenter = RecipeListPresenter.init(fragment);
    }

    @Test
    public void start_showList(){
        presenter.start();
      //  verify(fragment).showList();
    }

}