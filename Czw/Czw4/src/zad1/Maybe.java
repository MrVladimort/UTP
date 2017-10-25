package zad1;

import java.util.NoSuchElementException;
import java.util.function.*;

public class Maybe<T> {
	private T value;

	private Maybe(T value){
		this.value = value;
	}
	
	static <T>Maybe<T> of(T s) {
		return new Maybe<>(s);
	}

	void ifPresent(Consumer<T> cons) {
		if(isPresent())
			cons.accept(this.value);
	}

	<V>Maybe<V> map(Function<T,V> func) {
		if(isPresent())
			return new Maybe<>(func.apply(this.value));
		else
			return new Maybe<>(null);
	}

	T get() {
		if(!isPresent())
			throw new NoSuchElementException("  maybe is empty");
		return this.value;
	}
	
	private boolean isPresent(){
		return this.value != null;
	}

	T orElse(T defVal) {
		if(isPresent())
			return this.value;
		else
			return defVal;
	}

	Maybe<T> filter(Predicate<T> pre) {
		if(!pre.test(this.value))
			return new Maybe<>(null);
		return this;
	}
	
	public String toString(){
		return isPresent() ? "Maybe has value " + this.value : "Maybe is empty";
	}
}
