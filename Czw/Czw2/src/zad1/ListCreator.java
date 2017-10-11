/**
 * @author Hravchenko Vladyslav S15567
 */

package zad1;

import java.util.*;

public class ListCreator<T> {
    List<T> list;

    private ListCreator(List<T> list) {
        this.list = list;
    }

    public static ListCreator<?> collectFrom(List<?> list) {
        return new ListCreator<>(list);
    }

    public ListCreator<T> when(Selector sel) {
        for (T arg : this.list)
            if (!sel.select(arg))
                this.list.remove(arg);

        return this;

        this.list.
    }

    public List<Integer> mapEvery(Mapper map) {
        return null;
    }
}
