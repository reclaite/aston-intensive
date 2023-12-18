package collection.sorter;

import com.reclaite.collection.SortableList;
import com.reclaite.collection.sorter.QuickSorter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

public class QuickSorterTest {

    @Test
    public void integerSortTest1() {
        SortableList<Integer> list = new SortableList<>();

        list.addAll(
                Arrays.asList(
                        1, 69, 64, 41, 124, 623, 5, -12, 592, 94, -1421, -22
                )
        );

        SortableList<Integer> exceptedList = new SortableList<>();
        exceptedList.addAll(
                Arrays.asList(
                        -1421, -22, -12, 1, 5, 41, 64, 69, 94, 124, 592, 623
                )
        );

        list.sort(new QuickSorter<>(Integer::compareTo));
        Assertions.assertIterableEquals(exceptedList, list);
    }

    @Test
    public void integerSortTest2() {
        SortableList<Integer> list = new SortableList<>();

        list.addAll(
                Arrays.asList(
                        -23, -524, -512, -62, -50, -10, 0, -555, -556, -423
                )
        );

        SortableList<Integer> exceptedList = new SortableList<>();
        exceptedList.addAll(
                Arrays.asList(
                        -556, -555, -524, -512, -423, -62, -50, -23, -10, 0
                )
        );

        list.sort(new QuickSorter<>(Integer::compareTo));
        Assertions.assertIterableEquals(exceptedList, list);
    }

    @Test
    public void stringLengthSortTest1() {
        SortableList<String> list = new SortableList<>();

        list.addAll(
                Arrays.asList(
                        "Привет", "Что", "Это", "Вау круто"
                )
        );

        SortableList<String> exceptedList = new SortableList<>();
        exceptedList.addAll(
                Arrays.asList(
                        "Это", "Что", "Привет", "Вау круто"
                )
        );

        list.sort(new QuickSorter<>(Comparator.comparingInt(String::length)));
        Assertions.assertIterableEquals(exceptedList, list);
    }
}
