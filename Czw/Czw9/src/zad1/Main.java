package zad1;

import java.io.IOException;

public class Main {
    public static void main(String [] args) throws IOException {
        if (args.length == 1) {
            CharScan tmp1 = new CharScan(args[0]);
            tmp1.showResults();
        } else if (args.length == 2) {
            CharScan tmp2 = new CharScan(args[0], args[1]);
            tmp2.showResults();
        }
    }
}
