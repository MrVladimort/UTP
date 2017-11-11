/**
 *
 *  @author Hravchenko Vladyslav S15567
 *
 */

package zad3;

import java.io.*;
import java.util.*;
import java.util.function.*;

public class Main {
  public static void main(String[] args) {
    /*
     *  definicja operacji w postaci lambda-wyrażeń:
     *  - flines - zwraca listę wierszy z pliku tekstowego
     *  - join - łączy napisy z listy (zwraca napis połączonych ze sobą elementów listy napisów)
     *  - collectInts - zwraca listę liczb całkowitych zawartych w napisie
     *  - sum - zwraca sumę elmentów listy liczb całkowitych
     */
	  Function <String, List<String>> flines = path -> {
			ArrayList<String> kek = new ArrayList<String>();
			Scanner sc = new Scanner(new FileReader(path));
			while(sc.hasNextLine())
				kek.add(sc.nextLine());
			sc.close();
	
			return kek;
	  };
		
	  Function <List<String>, String> join = list -> {
			String kek = "";
			for(String tmp : list){
				kek += tmp;
			}
			return kek;
		};
				
		Function<String, List<Integer>> collectInts = text -> {
			ArrayList<Integer> kek = new ArrayList<Integer>();
			String tmp = text.replaceAll("\\D+", " ");
			Scanner sc = new Scanner(tmp);
			while(sc.hasNextInt())
				kek.add(sc.nextInt());
			sc.close();
			return kek;
		};
		
		Function<List<Integer>, Integer> sum = ints -> {
			int kek = 0;
			for(Integer tmp : ints){
				kek+=tmp;
			}
			return kek;
		};  
		
    String fname = System.getProperty("user.home") + "/LamComFile.txt"; 
    InputConverter<String> fileConv = new InputConverter<>(fname);
    List<String> lines = fileConv.convertBy(flines);
    String text = fileConv.convertBy(flines, join);
    List<Integer> ints = fileConv.convertBy(flines, join, collectInts);
    Integer sumints = fileConv.convertBy(flines, join, collectInts, sum);

    System.out.println(lines);
    System.out.println(text);
    System.out.println(ints);
    System.out.println(sumints);

    List<String> arglist = Arrays.asList(args);
    InputConverter<List<String>> slistConv = new InputConverter<>(arglist);  
    sumints = slistConv.convertBy(join, collectInts, sum);
    System.out.println(sumints);

    // Przy powierzchownej implementacji
    // następujący fragment:
    slistConv.convertBy(collectInts, sum); // [1] spowoduje powstanie wyjątku ClassCastException

    // Zadania badawcze:
    // jak temu zaradzić w fazie kompilacji programu, tak by kompilator oznaczył [1] jako błąd
    // - wymaga odpowiedniej definicji klasy InputConverter, wszelkie modyfikacje klasy Main są też dozwolone, 
    // ale niekonieczne (a nawet niepotrzebne)  
  }
}
