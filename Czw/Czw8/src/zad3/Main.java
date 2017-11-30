/**
 * @author Hravchenko Vladyslav S15567
 */
package zad3;
import java.net.*;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
public class Main {
    public static void main(String[] args) throws IOException {
        List<String> list = new ArrayList<>();
        BufferedReader in = new BufferedReader(new InputStreamReader(new URL("http://www.puzzlers.org/pub/wordlists/unixdict.txt").openStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) list.add(inputLine);
        in.close();
        list.stream().collect(Collectors.groupingBy((String w) -> {
            char[] tab = w.toLowerCase().toCharArray();
            Arrays.sort(tab);
            return String.valueOf(tab);
        })).entrySet().stream().sorted((Map.Entry<String, List<String>> obj1, Map.Entry<String, List<String>> obj2) ->
                Integer.compare(obj2.getValue().size(), obj1.getValue().size())).forEach(e -> {
            for (String tmp : e.getValue())
                System.out.print(tmp + " ");
            System.out.println();
        });
    }
}