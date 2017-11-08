package ru.sukharev.myrecipes.pojo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Class presenting a recipe which stores in database
 */
@Entity
public class Recipe {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String title;

    public int rating;

    public String description;

}
