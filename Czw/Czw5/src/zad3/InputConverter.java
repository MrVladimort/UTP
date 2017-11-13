package zad3;

import java.util.function.*;

public class InputConverter<T> {
	T value;
	
	public InputConverter(T value){
		this.value = value;
	}
	<S, V>S convertBy(Function<T, V> kompilator, Function...arg){
		Object kek = value;
		kek = kompilator.apply((T) kek);

		for(Function func : arg)
			kek = func.apply(kek);
		return (S) kek;
	}
}
