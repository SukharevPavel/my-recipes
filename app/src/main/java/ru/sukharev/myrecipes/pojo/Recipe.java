package ru.sukharev.myrecipes.pojo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Class presenting a recipe which stores in database
 */
@Entity
public class Recipe {

    static final String ID = "id";
    private static final int MIN_RATING = 0;
    private static final int MAX_RATING = 5;

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    public int id;

    public String title;

    public int rating;

    public String description;

    public static class Builder {

        private Recipe recipe;

        public Builder(){
            recipe = new Recipe();
        }

        public Builder setTitle(String title) {
            recipe.title = title;
            return this;
        }

        public Builder setRating(int rating) {
            if (rating < 0 || rating > 5) {
                throw new IllegalArgumentException("Rating should be between " +
                Recipe.MIN_RATING + " and " + Recipe.MAX_RATING + ", but current value is " + rating);
            } else {
                recipe.rating = rating;
            }
            return this;
        };

        public Builder setDescription(String description){
            recipe.description = description;
            return this;
        }

        public Recipe build(){
            return recipe;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Recipe recipe = (Recipe) o;

        if (rating != recipe.rating) {
            return false;
        }
        if (title != null ? !title.equals(recipe.title) : recipe.title != null) {
            return false;
        }
        return description != null ? description.equals(recipe.description) : recipe.description == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + rating;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
