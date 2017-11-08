package ru.sukharev.myrecipes.base;

/**
 * Base class for all view-layer objects in MVP
 */

public interface BaseView<T> {

    void setPresenter(T presenter);
}
