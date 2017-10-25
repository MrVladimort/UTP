package zad1;

import java.util.function.*;

public class Maybe<T> {
	
	public Maybe(){
		
	}
	
	public static <T>Maybe<T> of(T s) {
		// TODO Auto-generated method stub
		return null;
	}

	public void ifPresent(Consumer<T> cons) {
		// TODO Auto-generated method stub
	}

	public <V>Maybe<V> map(Function<T,V> func) {
		// TODO Auto-generated method stub
		return null;
	}

	public T get() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean isPresent(){
		// TODO Auto-generated method stub
		return false;
	}

	public T orElse(T defVal) {
		// TODO Auto-generated method stub
		return null;
	}

	public Maybe<T> filter(Predicate<T> pre) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
