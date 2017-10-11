/**
 *
 *  @author Hravchenko Vladyslav S15567
 *
 */

package zad1;

import java.util.*;

public class ListCreator <T>{
	List<T> list; 
	
	private ListCreator(List<T> list){
		this.list = list;
	}
	
	public static ListCreator<?> collectFrom(List <?> list){
		return new ListCreator<>(list);
	}
	
	public ListCreator<T> when(Selector<T> sel){
		for(T arg : this.list)
			if(!sel.select(arg))
				this.list.remove(arg);
		
		return this;
	}
	
	public List<Integer> mapEvery(Mapper<?,Integer> map){
		int [] tmp = new int [this.list.size()];
		for(int i = 0; i < tmp.length; i++)
			tmp[i] = map.map(this.list.get(i));
		
		return new List<Integer>(tmp);
	}
}  
