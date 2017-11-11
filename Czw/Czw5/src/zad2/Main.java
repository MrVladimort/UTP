/**
 *
 *  @author Hravchenko Vladyslav S15567
 *
 */

package zad2;

import java.io.*;
import java.util.*;
import java.util.function.*;

/*<-- niezbędne import */
public class Main {
  public static void main(String[] args) throws IOException {
	  Converter <String, List<String>> flines = path -> {
			ArrayList<String> kek = new ArrayList<String>();
			Scanner sc = new Scanner(new FileReader(path));
			while(sc.hasNextLine())
				kek.add(sc.nextLine());
			sc.close();
			return kek;
	  };

	  Converter <List<String>, String> join = list -> {
			String kek = "";
			for(String tmp : list){
				kek += tmp;
			}
			return kek;
		};

	  Converter<String, List<Integer>> collectInts = text -> {
			ArrayList<Integer> kek = new ArrayList<Integer>();
			String tmp = text.replaceAll("\\D+", " ");
			Scanner sc = new Scanner(tmp);
			while(sc.hasNextInt())
				kek.add(sc.nextInt());
			sc.close();
			return kek;
		};
		
		Converter<List<Integer>, Integer> sum = ints -> {
			int kek = 0;
			for(Integer tmp : ints)
				kek+=tmp;
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

    // Zadania badawcze:
    // Operacja flines zawiera odczyt pliku, zatem może powstac wyjątek IOException
    // Wymagane jest, aby tę operację zdefiniowac jako lambda-wyrażenie
    // Ale z lambda wyrażeń nie możemy przekazywac obsługi wyjatków do otaczającego bloku
    // I wobec tego musimy pisać w definicji flines try { } catch { }
    // Jak spowodować, aby nie było to konieczne i w przypadku powstania wyjątku IOException
    // zadziałała klauzula throws metody main
  }
}
