package ru.sukharev.myrecipes.pojo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Class for storing ingredients in Room
 */
@Entity
public class Ingredient {

    final static String ID = "id";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    public int id;

    public String name;

    public float protein;

    public float fat;

    public float carbohydrate;

    public float price;

    public float calories;


}
