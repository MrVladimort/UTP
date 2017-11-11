package zad2;

import java.util.function.*;

public class InputConverter<T> {
	T value;
	
	public InputConverter(T value){
		this.value = value;
	}
	
	<V>V convertBy(Converter...arg){
		Object kek = value;
		for(Converter func : arg)
			kek = func.apply(kek);
		return (V) kek;
	}
}
