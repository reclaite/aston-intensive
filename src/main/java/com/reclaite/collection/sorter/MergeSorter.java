package com.reclaite.collection.sorter;

import com.reclaite.collection.SortableList;

import java.util.Comparator;

public class MergeSorter<E> extends ListSorter<E> {

    public MergeSorter(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    public void sort(SortableList<E> list) {
        mergeSort(list, 0, list.size() - 1);
    }

    private void mergeSort(SortableList<E> list, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(list, start, mid);
            mergeSort(list, mid + 1, end);
            merge(list, start, mid, end);
        }
    }

    @SuppressWarnings("unchecked")
    private void merge(SortableList<E> list, int low, int mid, int high) {
        int leftSize = mid - low + 1;
        int rightSize = high - mid;

        E[] leftArray = (E[]) new Object[leftSize];
        E[] rightArray = (E[]) new Object[rightSize];

        for (int i = 0; i < leftSize; i++) {
            leftArray[i] = list.get(low + i);
        }

        for (int j = 0; j < rightSize; j++) {
            rightArray[j] = list.get(mid + 1 + j);
        }

        int i = 0, j = 0, k = low;

        while (i < leftSize && j < rightSize) {
            if (compare(leftArray[i], rightArray[j])) {
                list.set(k, leftArray[i]);
                i++;
            } else {
                list.set(k, rightArray[j]);
                j++;
            }
            k++;
        }

        while (i < leftSize) {
            list.set(k, leftArray[i]);
            i++;
            k++;
        }

        while (j < rightSize) {
            list.set(k, rightArray[j]);
            j++;
            k++;
        }
    }
}
