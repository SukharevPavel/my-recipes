package ru.sukharev.myrecipes.addrecipe;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import ru.sukharev.myrecipes.R;


public class AddRecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        AddRecipeFragment fragment = (AddRecipeFragment) getSupportFragmentManager().findFragmentById(R.id.add_recipe_fragment);
        AddRecipePresenter.init(this, fragment);
        FloatingActionButton floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Listener listener = (Listener) AddRecipeActivity.this.getSupportFragmentManager().findFragmentById(R.id.add_recipe_fragment);
                    listener.onFabClick();
                } catch (ClassCastException ex) {
                    throw new AssertionError("inner fragment must implements activity listener");
                }
            }
        });
    }


    //used for passing activity events to fragments
    public interface Listener {

        void onFabClick();

    }

}
