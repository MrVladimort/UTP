package zad2;

public class Main {
    public static void main(String[] arg) {
        Dictionary dictionary = new Dictionary(System.getProperty("user.home") + "/dictionary.txt");

        System.out.println(dictionary.lookup("apple"));
        dictionary.update("apple", "bog", "huyog");
        System.out.println(dictionary.lookup("apple"));

        System.out.println(dictionary.lookup("windows"));
        dictionary.add("windows", "bog");
        System.out.println(dictionary.lookup("windows"));

        System.out.println(dictionary.lookup("java"));
        dictionary.delete("java", 3);
        System.out.println(dictionary.lookup("java"));

        dictionary.save(System.getProperty("user.home") + "/newdictionary.txt");
    }
}
