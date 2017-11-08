package ru.sukharev.myrecipes.pojo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;

import java.util.List;

/**
 * Class presenting a recipe which stores in database
 */
@Entity
public class Recipe {

    static final String ID = "id";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    public int id;

    public String title;

    public int rating;

    public String description;

    @Relation(parentColumn = ID, entityColumn = RecipeIngredient.RECEIPT_ID)
    public List<RecipeIngredient> ingredients;

}
