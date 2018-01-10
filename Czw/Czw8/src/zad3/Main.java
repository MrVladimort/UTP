/**
 * @author Hravchenko Vladyslav S15567
 */
package zad3;
import java.net.*;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
public class Main {
    private static int max = 0;
    public static void main(String[] args) throws IOException {
        new BufferedReader(new InputStreamReader(new URL("http://www.puzzlers.org/pub/wordlists/unixdict.txt").openStream())).lines().collect(Collectors.groupingBy((String tmp) -> Arrays.toString(tmp.chars().mapToObj(e -> (char) e).sorted().toArray()))).values().stream().sorted((obj1, obj2) -> obj2.size() - obj1.size()).forEach(e -> {
            if (max < e.size()) max = e.size();
            if (e.size() == max) {
                for (String word : e) System.out.print(word + " ");
                System.out.println();
            } else System.exit(0);
        });
    }
}