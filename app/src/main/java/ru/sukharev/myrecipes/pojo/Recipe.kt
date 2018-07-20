package ru.sukharev.myrecipes.pojo

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Class presenting a recipe which stores in database
 */
@Entity
class Recipe {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    var id: Int = 0

    var title: String? = null

    var rating: Int = 0

    var description: String? = null

    class Builder {

        private val recipe: Recipe = Recipe()

        fun setTitle(title: String): Builder {
            recipe.title = title
            return this
        }

        fun setRating(rating: Int): Builder {
            if (rating < 0 || rating > 5) {
                throw IllegalArgumentException("Rating should be between " +
                        Recipe.MIN_RATING + " and " + Recipe.MAX_RATING + ", but current value is " + rating)
            } else {
                recipe.rating = rating
            }
            return this
        }

        fun setDescription(description: String): Builder {
            recipe.description = description
            return this
        }

        fun build(): Recipe {
            return recipe
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other == null || javaClass != other.javaClass) {
            return false
        }

        val recipe = other as Recipe?

        if (rating != recipe!!.rating) {
            return false
        }
        if (if (title != null) title != recipe.title else recipe.title != null) {
            return false
        }
        return if (description != null) description == recipe.description else recipe.description == null
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + if (title != null) title!!.hashCode() else 0
        result = 31 * result + rating
        result = 31 * result + if (description != null) description!!.hashCode() else 0
        return result
    }

    companion object {

        private const val ID = "id"
        private const val MIN_RATING = 0
        private const val MAX_RATING = 5
    }
}
