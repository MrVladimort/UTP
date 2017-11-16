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
                if (dictWord.length == 2)
                    if (this.dict.containsKey(dictWord[0].trim())) {
                        this.dict.get(dictWord[0].trim()).add(dictWord[1].trim());
                    } else {
                        List<String> tmp = new ArrayList<>();
                        tmp.add(dictWord[1].trim());
                        this.dict.put(dictWord[0].trim(), tmp);
                    }
            }
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

