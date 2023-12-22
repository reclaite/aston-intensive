package collection;

import com.reclaite.collection.SortableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

public class SortableListTest {

    @Test
    public void addTest1() {
        SortableList<Integer> list = new SortableList<>();
        list.add(34);
        list.addAll(Arrays.asList(1, 5, 3));
        list.add(1, 234);

        Assertions.assertIterableEquals(Arrays.asList(34, 234, 1, 5, 3), list);
    }

    @Test
    public void addTest2() {
        Assertions.assertThrows(
                ArrayIndexOutOfBoundsException.class,
                () -> new SortableList<>().add(1, 2)
        );
    }

    @Test
    public void addTest3() {
        SortableList<Integer> list = new SortableList<>();
        list.addAll(Arrays.asList(2, 3, 5, 1));
        list.addAll(1, Arrays.asList(7, 6, 5));

        Assertions.assertIterableEquals(Arrays.asList(2, 7, 6, 5, 3, 5, 1), list);
    }

    @Test
    public void removeTest1() {
        SortableList<Integer> list = new SortableList<>();
        list.addAll(Arrays.asList(3, 5, 10, 312));
        list.removeAll(Arrays.asList(10, 3));

        Assertions.assertEquals(Arrays.asList(5, 312), list);
    }

    @Test
    public void removeTest2() {
        SortableList<Integer> list = new SortableList<>();
        list.addAll(Arrays.asList(3, 5, 10, 312));
        list.remove(1);

        Assertions.assertEquals(Arrays.asList(3, 10, 312), list);
    }

    @Test
    public void removeTest3() {
        SortableList<Integer> list = new SortableList<>();
        list.addAll(Arrays.asList(3, 5, 10, 312, 512, 412, 32));
        list.remove(4);
        list.remove(1);
        list.remove(2);

        Assertions.assertEquals(Arrays.asList(3, 10, 412, 32), list);
    }

    @Test
    public void removeTest4() {
        SortableList<Integer> list = new SortableList<>();
        list.addAll(Arrays.asList(3, 5, 10, 312, 621, 412));
        list.removeAll(Arrays.asList(10, 312));

        Assertions.assertEquals(Arrays.asList(3, 5, 621, 412), list);
    }

    @Test
    public void getTest1() {
        SortableList<Integer> list = new SortableList<>();
        list.addAll(Arrays.asList(5, 3, 32, 14, 52));

        Assertions.assertEquals(32, list.get(2));
    }

    @Test
    public void getTest2() {
        SortableList<Integer> list = new SortableList<>();
        list.addAll(Arrays.asList(5, 3, 32, 14, 52));
        list.remove(2);

        Assertions.assertEquals(14, list.get(2));
    }

    @Test
    public void isEmptyTest1() {
        SortableList<Integer> list = new SortableList<>();
        list.add(2);
        list.remove(0);

        Assertions.assertTrue(list.isEmpty());
    }

    @Test
    public void isEmptyTest2() {
        SortableList<Integer> list = new SortableList<>();
        list.addAll(Arrays.asList(4, 4, 2, 1, 5, 53, 3));
        list.clear();

        Assertions.assertTrue(list.isEmpty());
    }

    @Test
    public void clearTest() {
        SortableList<Integer> list = new SortableList<>();
        list.addAll(Arrays.asList(4, 4, 2, 1, 5, 53, 3));
        list.clear();

        Assertions.assertIterableEquals(Collections.emptyList(), list);
    }
}
