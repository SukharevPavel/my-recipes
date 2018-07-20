package ru.sukharev.myrecipes.pojo

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Class for storing ingredients in Room
 */
@Entity
class Ingredient {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    var id: Int = 0

    var name: String? = null

    var protein: Float = 0.toFloat()

    var fat: Float = 0.toFloat()

    var carbohydrate: Float = 0.toFloat()

    var price: Float = 0.toFloat()

    var calories: Float = 0.toFloat()

    companion object {

        internal const val ID = "id"
    }

    interface Clickable {
        fun click()
        fun showOff() = println("I'm clickable!")
    }

}