package zad1;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class CharScan {
    private HashMap<Character, Integer> chars = new HashMap<>();
    private HashMap<String, Integer> words = new HashMap<>();
    private TreeMap<Character, Integer> sortedChars = new TreeMap<>();
    private TreeMap<Character, Integer> sortedWords = new TreeMap<>();
    private boolean hasWord = false;
    private String word;
    private Path path;

    CharScan(String path) throws IOException {
        this.path = Paths.get(path);
        Files.lines(this.path)
                .forEach((String line) -> line.chars().mapToObj(integer -> (char) integer).forEach(this::setChars));
        System.out.println(this.chars);
    }

    CharScan(String path, String word) throws IOException {
        this.path = Paths.get(path);
        this.word = word;
        Files.lines(this.path)
                .forEach((String line) -> Arrays.stream(line.split(" ")).forEach(badWord -> {
                    badWord.chars().mapToObj(integer -> (char) integer).forEach(this::setChars);
                    String tmp = badWord.replaceAll("\\W", "");
                    if (tmp.equalsIgnoreCase(this.word))
                        this.hasWord = true;
                    this.setWords(tmp);
                }));
    }

    private void setWords(String word) {
        if (this.words.containsKey(word))
            this.words.replace(word, this.words.get(word) + 1);
        else
            this.words.put(word, 1);
    }

    private void setChars(Character character) {
        if (this.chars.containsKey(character))
            this.chars.replace(character, this.chars.get(character) + 1);
        else
            this.chars.put(character, 1);
    }

    public void showResults() {
        System.out.println(this.path);
        System.out.println("=========================================");
        System.out.println("Chars");
        if (!this.chars.isEmpty())
            for (Character tmp : this.chars.keySet())
                System.out.println(tmp + ": " + this.chars.get(tmp));

        if(!this.words.isEmpty()) {
            System.out.println("=========================================");
            System.out.println("Words");
            for (String tmp : this.words.keySet())
                System.out.println(tmp + ": " + this.words.get(tmp));
        }

        if(this.word != null) {
            System.out.println("=========================================");
            System.out.println("Hasword");
            System.out.println(this.word + ": " + this.hasWord);
            System.out.println("=========================================");
        }
    }
}
