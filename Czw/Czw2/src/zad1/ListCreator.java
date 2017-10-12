/**
 * @author Hravchenko Vladyslav S15567
 */

package zad1;

import java.util.*;

public class ListCreator<T> {
    private ArrayList<T>list;

    private ListCreator(List<T> list) {
        this.list = new ArrayList<>(list);
    }

    public static <E>ListCreator<E> collectFrom(List<E> list) {
        return new ListCreator<>(list);
    }

    public ListCreator<T> when(Selector <T> sel) {
        ArrayList<T> tmp = new ArrayList<>();
        for(T arg : this.list)
            if(!sel.select(arg))
                tmp.add(arg);

        return new ListCreator<>(tmp);
    }

    public <E> List<E> mapEvery(Mapper<T,E> map) {
        ArrayList<E>tmp = new ArrayList<>();
        for(T arg : this.list)
            tmp.add(map.map(arg));
        return tmp;
    }
}
