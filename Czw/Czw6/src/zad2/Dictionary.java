package zad2;

import java.io.*;
import java.util.*;

public class Dictionary {
    HashMap<String, List<String>> dict = new HashMap<>();

    Dictionary(String filePath){
        try {
            Scanner sc = new Scanner(new FileReader(filePath));
            while (sc.hasNextLine()){
                String [] dictWord = sc.nextLine().split("=");
                if (dictWord.length == 2) {
                    String haslo = dictWord[0].trim(), definicja = dictWord[1].trim();
                    if (!haslo.equals("") && !definicja.equals(""))
                        if (this.dict.containsKey(haslo))
                            this.dict.get(haslo).add(definicja);
                        else {
                            List<String> tmp = new ArrayList<>();
                            tmp.add(definicja);
                            this.dict.put(haslo, tmp);
                        }
                }
            }
            System.out.println(this.dict);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<String> lookup() {
        return null;
    }

    void add() {

    }

    void delete() {

    }

    void update() {

    }

    void save() {

    }
}

