package zad2;

import java.io.*;
import java.util.*;

public class Dictionary {
    HashMap<String, List<String>> dict = new HashMap<>();

    Dictionary(String filePath){
        try {
            Scanner sc = new Scanner(new FileReader(filePath));
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                this.dict.put()
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

