/**
 * @author Hravchenko Vladyslav S15567
 */

package zad1;

import java.io.*;
import java.util.*;

public class Anagrams {
    List<List<String>> anagrams = new ArrayList<>();
    Anagrams(String path) {
        try {
            ArrayList<String> all = new ArrayList<>();
            Scanner sc = new Scanner(new FileReader(new File(path)));
            while (sc.hasNext()) {
                all.add(sc.next());
            }
            sc.close();

            for(int i = 0; i < all.size(); i++) {
                ArrayList <String> tmpAnagrams = new ArrayList<>();

                for (String aAll : all) {
                    if (anagram(all.get(i), aAll))
                        tmpAnagrams.add(aAll);
                }

                i--;

                all.removeAll(tmpAnagrams);
                Collections.sort(tmpAnagrams);
                this.anagrams.add(tmpAnagrams);
            }
        } catch (FileNotFoundException e) { e.printStackTrace(); }
    }

    Iterable<? extends List<String>> getSortedByAnQty() {
        List<List<String>> tmp = new ArrayList<>(this.anagrams);

        tmp.sort((o1, o2) -> {
                if (o1.size() == o2.size())
                    return o1.get(0).compareTo(o2.get(0));
                else
                    return Integer.compare(o1.size(), o2.size()) * -1;
        });

        return tmp;
    }

    String getAnagramsFor(String word) {
        for (List<String> tmp : this.anagrams){
            if (anagram(tmp.get(0), word)) {
                ArrayList<String> kek = new ArrayList<>(tmp);
                for (String anagram : kek)
                    if (anagram.equals(word)) {
                        kek.remove(anagram);
                        return word + ": " + kek;
                    }
            }
        }

        return word + ": " + null;
    }

    private boolean anagram(String s, String t) {
        if(s.length() != t.length())
            return false;

        int value = 0;
        for(int i = 0; i < s.length(); i++){
            value += ((int)s.charAt(i))^2;
            value -= ((int)t.charAt(i))^2;
        }
        return value == 0;
    }
}
