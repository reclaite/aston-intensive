package com.reclaite.collection.sorter;

import com.reclaite.collection.SortableList;

import java.util.Comparator;

public class QuickSorter<E> extends ListSorter<E> {

    public QuickSorter(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    public void sort(SortableList<E> list) {
        quickSortPartition(list, 0, list.size() - 1);
    }

    private void quickSortPartition(SortableList<E> list, int start, int end) {
        if (start < end) {
            int pivotIndex = pivotSwap(list, start, end);
            quickSortPartition(list, start, pivotIndex - 1);
            quickSortPartition(list, pivotIndex + 1, end);
        }
    }

    public int pivotSwap(SortableList<E> list, int low, int high) {
        E pivot = list.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (getComparator().compare(list.get(j), pivot) < 0) {
                i++;
                swap(list, i, j);
            }
        }

        swap(list, i + 1, high);
        return i + 1;
    }

    private void swap(SortableList<E> list, int i, int j) {
        E temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

}
