package zad3;

import java.io.IOException;
import java.util.function.Function;

interface Converter<T, R> extends Function <T, R> {
    @Override
    default R apply(T t) {
        try {
            R kek = exception(t);
            return kek;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (R) t;
    }

    R exception(T t) throws IOException;
}
