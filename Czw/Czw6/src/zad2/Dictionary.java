package zad2;

import java.io.*;
import java.util.*;

public class Dictionary {
    TreeMap<String, List<String>> dict = new TreeMap<>();

    Dictionary(String path) {
        try {
            Scanner sc = new Scanner(new FileReader(path));
            while (sc.hasNextLine()) {
                String[] dictWord = sc.nextLine().split("=");
                if (dictWord.length == 2)
                    add(dictWord[0].trim(), dictWord[1].trim());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    List<String> lookup(String haslo) {
        if (this.dict.containsKey(haslo)) {
            List<String> tmp = new ArrayList<>(this.dict.get(haslo));
            tmp.sort(Comparator.naturalOrder());
            for (int i = 0; i < tmp.size(); i++)
                tmp.set(i, i+1 + ": " + tmp.get(i));
//            tmp.add(0, haslo);
            return tmp;
        } else {
            return new ArrayList<>();
        }
    }

    void add(String haslo, String definicja) {
        if (!haslo.trim().equals("") && !definicja.trim().equals("")) {
            if (this.dict.containsKey(haslo)) {
                if (!this.dict.get(haslo).contains(definicja))
                    this.dict.get(haslo).add(definicja);
            } else {
                List<String> tmp = new ArrayList<>();
                tmp.add(definicja);
                this.dict.put(haslo, tmp);
            }
        }
    }

    void delete(String haslo, int definicja) {
        if (this.dict.containsKey(haslo)) {
            if (this.dict.get(haslo).size() >= definicja) {
                String del = this.lookup(haslo).get(definicja-1).split(":")[1].trim();
                this.dict.get(haslo).remove(del);
            }
        }
    }

    void update(String haslo, String stara, String nowa) {
        if (this.dict.containsKey(haslo)) {
            if (!nowa.trim().equals("") && this.dict.get(haslo).contains(stara)) {
                int index = this.dict.get(haslo).indexOf(stara);
                this.dict.get(haslo).set(index, nowa);
            }
        }
    }

    void save(String path) {
        try {
            FileWriter fr = new FileWriter(new File(path));
            for (String haslo : this.dict.keySet())
                for (String definicja : this.dict.get(haslo))
                    fr.write(haslo+"="+definicja+'\n');
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

