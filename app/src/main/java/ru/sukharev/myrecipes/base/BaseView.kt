package ru.sukharev.myrecipes.base

/**
 * Base class for all view-layer objects in MVP
 */

interface BaseView<T> {

    fun setPresenter(presenter: T)
}
