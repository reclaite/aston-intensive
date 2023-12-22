package com.reclaite.collection;

import com.reclaite.collection.sorter.ListSorter;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SortableList<E> extends AbstractList<E> {

    private static final int DEFAULT_CAPACITY = 16;
    private static final float EXTEND_RATIO = 1.5F;

    private Object[] data;
    private int size;

    /**
     * Sorts this list by the specified sorter
     * You can write your own algorithm or use default realizations
     * such as {@link com.reclaite.collection.sorter.QuickSorter} or {@link com.reclaite.collection.sorter.MergeSorter}
     *
     * @param sorter sorting algorithm object
     */
    public void sort(ListSorter<E> sorter) {
        // Skip sorting if it is not necessary
        if (size() <= 2) {
            return;
        }
        try {
            sorter.sort(this);
        } catch (Exception exception) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Caught exception while sorting list", exception);
        }
    }

    public SortableList() {
        this(DEFAULT_CAPACITY);
    }

    public SortableList(int capacity) {
        this.data = new Object[capacity];
    }

    private void extendData(int preferredExtension) {
        Object[] newData = new Object[data.length + preferredExtension];
        for (int i = 0; i < size; i++) {
            Object object = data[i];
            newData[i] = object;
        }
        data = newData;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) {
            return false;
        }

        for (Object object : data) {
            if (o.equals(object)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean add(E e) {
        add(size, e);
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean remove(Object o) {
        for (int i = 0, dataLength = data.length; i < dataLength; i++) {
            Object object = data[i];
            if (!o.equals(object)) {
                continue;
            }

            remove(i);
            return true;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object object : collection) {
            if (!contains(object)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        checkCollectionExtend(collection.size());

        Iterator<? extends E> iterator = collection.iterator();
        while (iterator.hasNext()) {
            add(iterator.next());
        }

        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean addAll(int index, Collection<? extends E> collection) {
        int collectionSize = collection.size();
        checkCollectionExtend(collectionSize);

        copyTail(index, index, index + collectionSize);

        Iterator<? extends E> iterator = collection.iterator();
        int counter = index;
        while (iterator.hasNext()) {
            add(counter++, iterator.next());
        }

        return true;
    }

    private void checkCollectionExtend(int collectionSize) {
        int overallSize = size + collectionSize;
        if (overallSize > data.length) {
            extendData(overallSize - data.length);
        }
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        for (Object object : collection) {
            remove(object);
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        boolean modified = false;
        Iterator<E> iterator = iterator();
        while (iterator.hasNext()) {
            if (!collection.contains(iterator.next())) {
                iterator.remove();
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public void clear() {
        modCount++;
        for (int i = size = 0; i < data.length; i++) {
            data[i] = null;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        checkIndexInRange(index);
        return (E) data[index];
    }

    @Override
    @SuppressWarnings("unchecked")
    public E set(int index, E element) {
        checkIndexInRange(index);
        Object object = data[index];
        data[index] = element;
        return (E) object;
    }

    private void checkIndexInRange(int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void add(int index, E element) {
        checkIndexInRange(index);

        if (size == data.length) {
            extendData((int) (size * EXTEND_RATIO));
        }

        copyTail(index, index, index + 1);

        size++;
        data[index] = element;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E remove(int index) {
        Object object = data[index];
        data[index] = null;
        copyTail(index, index + 1, index);
        size--;
        return (E) object;
    }

    private void copyTail(int index, int srcPos, int destPos) {
        if (index < size - 1) {
            System.arraycopy(data, srcPos, data,
                    destPos, size - index);
        }
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            return -1;
        }

        for (int i = 0; i < data.length; i++) {
            Object object = data[i];
            if (o.equals(object)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            return -1;
        }

        for (int i = data.length - 1; i >= 0; i--) {
            Object object = data[i];
            if (o.equals(object)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public String toString() {
        return "SortableList{" +
                "data=" + Arrays.toString(data) +
                ", size=" + size +
                '}';
    }

}
