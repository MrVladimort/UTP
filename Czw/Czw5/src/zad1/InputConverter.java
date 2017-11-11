package zad1;

import java.util.function.*;

public class InputConverter<T> {
	T value;
	
	public InputConverter(T value){
		this.value = value;
	}
	
	<V>V convertBy(Function...arg){
		Object kek = value;
		for(Function func : arg)
			kek = func.apply(kek);
		return (V) kek;
	}
}
