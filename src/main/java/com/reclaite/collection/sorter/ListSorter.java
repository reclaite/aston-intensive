package com.reclaite.collection.sorter;

import com.reclaite.collection.SortableList;

import java.util.Comparator;

/**
 * Sorting predicate for {@link SortableList}
 * when you use {@link SortableList#sort(ListSorter)} it compares all the elements
 * by the initialized {@link Comparator} and make changes into current list
 *
 * @param <E>
 */
public abstract class ListSorter<E> {

    /**
     * Comparator which compares values
     * You can write your own comparable function or use lambdas such as {@link Integer#compareTo(Integer)}
     * or {@link String#compareTo(String)}, all the comparisons must return integers, see {@link #compare(Object, Object)}
     */
    private final Comparator<E> comparator;

    /**
     * List sorter default constructor
     *
     * @param comparator comparable function for list values
     */
    public ListSorter(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    /**
     * This method sorts your list by comparing values via {@link #comparator}
     * Calls at {@link SortableList#sort(ListSorter)} when list size is more than 1
     *
     * @param list list to sort
     */
    public abstract void sort(SortableList<E> list);

    /**
     * You can compare values with inner function
     *
     * @param first  first object of list
     * @param second second object of list
     * @return false if first value is less than second and true if first greater or equal second
     */
    public boolean compare(E first, E second) {
        return comparator.compare(first, second) < 0;
    }

}
