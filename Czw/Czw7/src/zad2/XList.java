package zad2;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class XList<T> extends ArrayList<T> {
    @SafeVarargs
    public XList(T... args) {
//        this.list = new ArrayList<>();
//        this.list.addAll(Arrays.asList(args));
        super(Arrays.asList(args));
    }

    public XList(Collection<T> args) {
//        this.list = args;
        super(args);
    }

    public XList() {
        super();
    }

    public static <T> XList<T> of(Collection<T> args) {
        return new XList<>(args);
    }

    @SafeVarargs
    public static <T> XList<T> of(T... args) {
        return new XList<>(args);
    }

    public static XList<String> charsOf(String arg) {
        Collection<String> collection = new ArrayList<>();
        char[] args = arg.toCharArray();

        for (char arg1 : args) collection.add(arg1 + "");

        return new XList<>(collection);
    }

    public static XList<String> tokensOf(String... args) {
        if (args.length == 1) {
            return new XList<>(args[0].split("/s"));
        } else {
            /*for (int i = 1; i < args.length; i++){
               args[0].split(args[i]);
            }*/
            return new XList<>(args[0].split(args[1]));
        }
    }

    public XList<T> union(Collection list) {
        XList<T> tmp = new XList<>(this);
        tmp.addAll(list);
        return tmp;
    }

    public XList<T> union(T... args) {
        XList<T> tmp = new XList<>(this);
        tmp.addAll(Arrays.asList(args));
        return tmp;
    }

    public XList<T> diff(Collection<T> set) {
        XList<T> tmp = new XList<>(this);
        tmp.removeAll(set);
        return tmp;
    }

    public XList<T> unique() {
        return new XList<>(new LinkedHashSet<>(this));
    }

    public void forEachWithIndex(BiConsumer<T, Integer> consumer) {
        XList<T> tmp = this;
        for (int i = 0; i < this.size(); i++) consumer.accept(tmp.get(i), i);
    }

    public XList<XList<T>> combine() {
        XList<XList<T>> combines = new XList<>();
        ArrayList<List> tmp = (ArrayList<List>) this;

        int size = 1;
        for (int i = 0; i < this.size(); i++){
            size = size * tmp.get(i).size();
        }
        for (int i = 0; i < size; i++){
            combines.add(new XList<>());
        }

        int semiSize = 1;
        for (List aTmp : tmp) {
            int position = 0;
            for (int i = 0, j = 0; i < size; i++, j++) {
                if (j == semiSize) {
                    position++;
                    j = 0;
                }

                if (aTmp.size() <= position)
                    position = 0;

                ((List) combines.get(i)).add(aTmp.get(position));
            }
            semiSize = semiSize * aTmp.size();
        }

        return combines;
    }

    public <R>XList<R> collect(Function<T,R> args){
        XList<R> collected = new XList<>();
        for (T t : this) collected.add(args.apply(t));
        return collected;
    }

    public String join(String string) {
        return new XList<>(this)
                .stream()
                .map(Object::toString)
                .collect(Collectors.joining(string));
    }

    public String join() {
        return new XList<>(this)
                .stream()
                .map(Object::toString)
                .collect(Collectors.joining(""));
    }
}
