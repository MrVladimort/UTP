/**
 * @author Hravchenko Vladyslav S15567
 */

package zad1;

import java.util.*;
import java.util.function.*;

public class ListCreator<T> {
    private ArrayList<T>list;

    private ListCreator(List<T> list) {
        this.list = new ArrayList<>(list);
    }

    public static <E>ListCreator<E> collectFrom(List<E> list) {
        return new ListCreator<>(list);
    }

    public ListCreator<T> when(Predicate<T>pre) {
        ArrayList<T> tmp = new ArrayList<>();
        for(T arg : this.list)
            if(pre.test(arg))
                tmp.add(arg);

        return new ListCreator<>(tmp);
    }

    public <E> List<E> mapEvery(Function<T,E> fun) {
        ArrayList<E>tmp = new ArrayList<>();
        for(T arg : this.list)
            tmp.add(fun.apply(arg));
        return tmp;
    }
}
