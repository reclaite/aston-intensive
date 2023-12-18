package com.reclaite.collection.sorter;

import com.reclaite.collection.SortableList;
import lombok.Getter;

import java.util.Comparator;

@Getter
public abstract class ListSorter<E> {

    private final Comparator<E> comparator;

    public ListSorter(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public abstract void sort(SortableList<E> list);

}
