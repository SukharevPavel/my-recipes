package ru.sukharev.myrecipes.pojo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * POJO that describes ingredient
 */

@Entity(foreignKeys = {@ForeignKey(entity = Ingredient.class,
        parentColumns = Ingredient.Companion.getID(),
        childColumns = RecipeIngredient.INGREDIENT_ID), @ForeignKey(entity = Recipe.class,
        parentColumns = Recipe.Companion.getID(),
        childColumns = RecipeIngredient.RECEIPT_ID)})
public class RecipeIngredient {

    static final String RECEIPT_ID = "receipt_id";
    static final String INGREDIENT_ID = "ingredient_id";

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = RECEIPT_ID)
    public int receiptId;

    @ColumnInfo(name = INGREDIENT_ID)
    public int ingredientId;

    public int amount;

}
